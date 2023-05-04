package com.example.myfirstspringapp.services;

import com.example.myfirstspringapp.data.WarehouseItem;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Getter
public class WarehouseService {
    RestTemplate connector=new RestTemplate();
    String url="http://localhost:8090/warehouse";

    List<String> order=new ArrayList<>();

    public List<WarehouseItem> getItemsFromWarehouse(){
        ResponseEntity<WarehouseItem[]> response=connector.getForEntity(url, WarehouseItem[].class);
        WarehouseItem[] items=response.getBody();
        return Arrays.stream(items).toList();
    }

    public List<String> addToOrder(String name){
        order.add(name);
        return  order;
    }

    public boolean makeOrder(){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object[]> request=new HttpEntity<>(order.toArray(),headers);
        System.out.println("Make order "+order.toArray());
        ResponseEntity<Boolean> response=connector.postForEntity(url+"/order",request,Boolean.class);
        System.out.println(response);
        boolean orderSucc=response.getBody();
        if(orderSucc) {
            System.out.println("Order successful");
            order.clear();
        } else System.out.println("Order not sent");
        return orderSucc;
    }



}
