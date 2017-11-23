//package com.javarush.task.task27.task2712;
//
//import com.javarush.task.task27.task2712.kitchen.Cook;
//import com.javarush.task.task27.task2712.kitchen.Order;
//import com.javarush.task.task27.task2712.statistic.StatisticManager;
//
//import java.util.Observable;
//import java.util.Observer;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class OrderManager implements Observer {
//
//    //private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
//
//    public OrderManager() {
//
//        Thread thread = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                while(true){
//                    Order order = orderQueue.peek();
//                    if (order != null){
//                        for (Cook cook : StatisticManager.getInstance().getCooks()){
//                            if (!cook.isBusy()){
//                                final Cook cookFinal = cook;
//                                final Order orderFinal = orderQueue.poll();
//                                Thread thr = new Thread(new Runnable(){
//                                    @Override
//                                    public void run()
//                                    {
//                                        cookFinal.startCookingOrder(orderFinal);
//                                    }
//                                });
//                                thr.start();
//                                break;
//                            }
//                        }
//                    }
//                    try{
//                        Thread.sleep(10);
//                    }catch (InterruptedException e){}
//                }
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
//
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        orderQueue.add((Order) arg);
//    }
//}
