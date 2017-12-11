///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package platformer2d.Postacie;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import platformer2d.Plansza;
//import static platformer2d.Plansza.mobArrayList;
//
///**
// *
// * @author Dunger
// */
//public class UsuwaczPostaci extends Thread{
//    
//            @Override
//            public void run() 
//            {
//                while(Plansza.GameState==Plansza.STATE.GAME)
//                {
//                    try {
//                        Thread.sleep(7);
//                        for(int i =0;i<mobArrayList.size();i++)
//                        {
//                            if(mobArrayList.get(i).umieranie==true)
//                            {
//                                try {
//                                    Thread.sleep(300);
//                                    mobArrayList.remove(i);
//                                } catch (InterruptedException ex) {
//                                    Logger.getLogger(Plansza.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        }
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(UsuwaczPostaci.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//            }
//            }
//      
//}
