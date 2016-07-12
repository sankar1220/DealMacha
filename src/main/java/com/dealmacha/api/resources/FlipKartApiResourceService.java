package com.dealmacha.api.resources;

import com.dealmacha.QueryService;
import com.dealmacha.api.Domain.FlipKartOrder;
import com.dealmacha.api.keys.ApiKeys;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

public class FlipKartApiResourceService implements ApiKeys {

    @Autowired
    private QueryService queryService;
    private String affliateId = FLIPKARTAFFILIATEID;
    private String token = FLIPKARTAFFILIATETOKEN;

    public List<FlipKartOrder> getFlipKartProductByOrderList(final String startDate, final String endDate, final String status,
            final String offset) {

        List<FlipKartOrder> orderList = new ArrayList<FlipKartOrder>();
        String requestUrl = "https://affiliate-api.flipkart.net/affiliate/report/orders/detail/json?startDate=" + startDate + "&endDate="
                + endDate + "&status=" + status + "&offset=" + offset;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponse(requestUrl).getEntity());
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json_string);
            final JSONObject json = (JSONObject) obj;
            JSONArray array = (JSONArray) json.get("orderList");
            for (int i = 0; i < array.size(); i++) {

                FlipKartOrder order = new FlipKartOrder();
                JSONObject ord = new JSONObject();
                order.setCategory(ord.get("category").toString());
                order.setPrice(ord.get("price").toString());
                order.setProductId(ord.get("productId").toString());
                order.setQuantity(ord.get("quantity").toString());
                JSONObject sales = (JSONObject) (ord.get("sales"));
                order.setSalesAmount(sales.get("amount").toString());
                order.setSalesCurrency(sales.get("currency").toString());
                order.setStatus(ord.get("status").toString());
                order.setAffiliateOrderItemId(ord.get("affiliateOrderItemId").toString());
                order.setOrderDate(ord.get("orderDate").toString());
                order.setCommissionRate(ord.get("commissionRate").toString());
                JSONObject tentativeCommission = (JSONObject) (ord.get("tentativeCommission"));
                order.setTentativeCommissionAmount(tentativeCommission.get("amount").toString());
                order.setTentativeCommissionCurrency(tentativeCommission.get("currency").toString());
                order.setAffExtParam1(ord.get("affExtParam1").toString());
                order.setSalesChannel(ord.get("salesChanel").toString());
                order.setCustomerType(ord.get("customerType").toString());
                orderList.add(order);
            }

        }
        catch (Exception exception) {

            exception.printStackTrace();
        }
        return orderList;

    }

}
