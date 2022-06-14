package com.yjc.test;

import com.yjc.entity.Customer;
import com.yjc.entity.Order;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class TestDrools {
    @Test
    public void test1(){
        //第一步:获取服务
        KieServices kieServices = KieServices.Factory.get();
        //第二步:获取容器
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //第三步:获取KieSession,由它去和规则引擎打交道交互
        KieSession kieSession = kieClasspathContainer.newKieSession();
        //事实对象
        Order order = new Order();
        order.setAmount(213);
        //第四步:数据交给规则引擎,去匹配规则
        kieSession.insert(order);

        //第五步:执行规则引擎(触发规则)
        kieSession.fireAllRules();
        //第六步:关闭kieSession
        kieSession.dispose();

        System.out.println("执行规则之后积分:"+order.getScore());
    }

    @Test
    public void test2(){
        //第一步:获取服务
        KieServices kieServices = KieServices.Factory.get();
        //第二步:获取容器
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //第三步:获取KieSession,由它去和规则引擎打交道交互
        KieSession kieSession = kieClasspathContainer.newKieSession();
        //事实对象
        Order order = new Order();
        Customer customer = new Customer();
        List<Order> list = new ArrayList<Order>();
        list.add(order);
        customer.setOrderList(list);
        customer.setName("张");
        //第四步:数据交给规则引擎,去匹配规则
        kieSession.insert(order);
        kieSession.insert(customer);

        //第五步:执行规则引擎(触发规则)
//        kieSession.fireAllRules();
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_3"));
        //第六步:关闭kieSession
        kieSession.dispose();

        System.out.println("执行规则之后积分:"+order.getScore());
    }
    @Test
    public void test3(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        kieSession.fireAllRules();

        kieSession.dispose();
    }

    @Test
    public void test4(){
        System.setProperty("drools.dateformat","yyyy-MM-dd");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        Customer cus = new Customer();
        cus.setName("李四");

        kieSession.insert(cus);
        
        kieSession.fireAllRules();

        kieSession.dispose();
    }

    @Test
    public void test5() throws InterruptedException {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

//        kieSession.getAgenda().getAgendaGroup("002");   //获得执行焦点
        new Thread(() -> {
            //启动规则引擎进⾏规则匹配，直到调⽤halt⽅法才结束规则引擎
            kieSession.fireUntilHalt();
        }).start();

        Thread.sleep(10000); //结束规则引擎
        kieSession.halt();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
