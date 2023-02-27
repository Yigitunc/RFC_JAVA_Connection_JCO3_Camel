public class RestRFCConfigure {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .enableCORS(true)
                .dataFormatProperty("prettyPrint", "true")
                .apiComponent("swagger")
                .apiContextPath("/api-doc");

        //REST POST SERVICES (JSON)
        rest("/api/flow")
                .consumes("application/json")
                .produces("application/json")
                .post("/createRFCDestinationModel").to("direct:createRFCDestinationModel");

        // CREATE RFC DESTINATION MODEL
        from("direct:createRFCDestinationModel")
                .description("Create SAP RFC")
                .routeId("createRFCDestinationModel")
                .setProperty("rfcBody", simple("${body}"))
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    exchange.setProperty("rfcBody", body);
                    RFCDestinationModel createRFCDestinationModel = new Gson().fromJson(body, RFCDestinationModel.class);

                    String createRFCDestinationModelQuery = String.format("INSERT INTO dbo.rfc_destinations(\"lang\", \"peak_limit\", \"client\"," +
                                    " \"password\", \"user\", \"sysnr\", \"pool_capacity\", \"ashost\") " +
                                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                            createRFCDestinationModel.getLang(),
                            createRFCDestinationModel.getPeak_limit(),
                            createRFCDestinationModel.getClient(),
                            createRFCDestinationModel.getPassword(),
                            createRFCDestinationModel.getUser(),
                            createRFCDestinationModel.getSysnr(),
                            createRFCDestinationModel.getPool_capacity(),
                            createRFCDestinationModel.getAshost());
                    exchange.getIn().setBody(createRFCDestinationModelQuery);
                })
                .to("jdbc:default")
                .choice()
                .when(simple("${header.CamelJdbcRowCount} == 0"))
                .bean(APIResponseBean.class, "customError('SAP RFC not created.',500,'U-CUC-500')")
                .otherwise()
                .process(exchange -> {
                    String body = exchange.getProperty("rfcBody").toString();
                    RFCDestinationModel createRFCDestinationModel = new Gson().fromJson(body, RFCDestinationModel.class);
                    RFCFileCreator fileCreator = new RFCFileCreator(
                            createRFCDestinationModel.getLang(),
                            createRFCDestinationModel.getPeak_limit(),
                            createRFCDestinationModel.getClient(),
                            createRFCDestinationModel.getPassword(),
                            createRFCDestinationModel.getUser(),
                            createRFCDestinationModel.getSysnr(),
                            createRFCDestinationModel.getPool_capacity(),
                            createRFCDestinationModel.getAshost()
                    );
                    fileCreator.CreateFile();
                })
                .bean(APIResponseBean.class, "successfulResponse('SAP RFC created.',200)")
                .to("direct://createRFCDestinationModel")
                .end();

    }
}
