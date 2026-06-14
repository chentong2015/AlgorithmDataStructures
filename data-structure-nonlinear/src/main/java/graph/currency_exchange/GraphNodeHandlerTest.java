package graph.currency_exchange;

public class GraphNodeHandlerTest {

    // EUR  -> USD  -> RMB  -> PLQ -> CHS
    //         CHS  -> RMB
    //
    public static void main(String[] args) {
        GraphNodeHandler handler = new GraphNodeHandler();
        handler.addExchange("EUR", "USD", 1.2f);
        handler.addExchange("CHS", "RMB", 1.4f);
        handler.addExchange("USD", "RMB", 2.0f);
        handler.addExchange("EUR", "USD", 1.5f); // 更新旧汇率
        handler.addExchange("RMB", "PLQ", 1.5f);
        // handler.addExchange("PLQ", "CHS", 1f); 测试循环节点

        System.out.println(handler.getExchangeRate("EUR", "PLQ"));
        System.out.println(handler.getExchangeRate("CHS", "PLQ"));
    }
}
