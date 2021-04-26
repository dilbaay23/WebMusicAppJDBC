package controller;

import entity.Customer;
import entity.Item;
import entity.Order;
import entity.Song;
import service.CustomerService;
import service.ItemService;
import service.OrderService;
import service.SongService;
import service.impl.CustomerServiceImpl;
import service.impl.ItemServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.SongServiceImpl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static utility.KeyboardUtility.*;
import static utility.MenuUtility.*;
import static utility.TextUtil.*;

/**
 * Created by Moon on 24/04/2021
 */
public class MainController {
    private int loginId= 0;
    private final SongService songService = new SongServiceImpl();
    private final CustomerService customerService= new CustomerServiceImpl();
    private final  OrderService orderService = new OrderServiceImpl();
    private final ItemService itemService = new ItemServiceImpl();

    public void start() {
        String line1= "iChoonz Web Shop Application[version 1.0.2-SNAPSHOT]";
        System.out.println(line1);
        String line2= "@2021 Moon. All rights reserved.";
        System.out.println(line2);
        System.out.println();
        System.out.println("Welcome to iChoonz");
        System.out.println("Your home for finding music you love");
        System.out.println();
        String title= "*   iChoonz    *";
        printTitleTop(title);
         showMenu();

    }

    private void showMenu() {
        String title= "*  Main menu   *";
        printTitle(title);
        String[] options = {"Browse music", "Log in", "Register", "Exit"};
        int chosenMenuOption = askForChoice(options);
        chooseMenuOption(chosenMenuOption);
    }


    private void chooseMenuOption(int chosenMenuOption) {

        switch(chosenMenuOption){
            case 0:
                browseMusic(loginId);
                break;
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                quit();
                break;
        }
    }

    private void register() {
        Customer customer = new Customer();
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("Welcome REGISTER Page"));
        System.out.println(thinLine());
        System.out.println(thickLine());

        customer.setFirstName(ask("Enter your name: "));
        customer.setLastName(ask("Enter your surname: "));
        customer.setBirthDate(askForFullDate("Enter your birthday:"));
        customer.setPhoneNumber(ask("Enter your phoneNumber with your Country code"));
        customer.setAddress(ask("Enter your address: "));
        customer.setCity(ask("Enter your city : "));
        customer.setCountry(ask("Enter your country : "));
        customer.setPassword(ask("Enter your password: "));
        boolean successRegister = customerService.save(customer);

        if (successRegister){
            System.out.println(thickLine());
            System.out.println(center("You will be redirected to the another Page"));
            login();
        }else{
            register();
        }
    }

    private void login() {
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("Welcome to LOGIN Page"));
        System.out.println(thinLine());
        System.out.println(thickLine());
        String phoneNumber = ask("Enter your Phone Number  : ");
        String password = ask("Enter your password : ");

        int checkId= customerService.checkCustomerForLogin(phoneNumber,password);
        if(checkId>0){
            loginId= checkId;
            browseMusic(loginId);
        }else {
            if(askYorN("Do you want to try again? "))
                login();
            else
                quit();
        }
    }




    private void browseMusic(int loginId) {
        String title= "*  Browse Music   *";
        printTitle(title);
        String[] options = {"By title", "By artist", "By genre", "By year", "Exit"};
        int chosenSearchTypeOption = askForChoice( options);
        searchSongBy(loginId, chosenSearchTypeOption);
        tildaLine();
        waitForLoading(3);
        showMenu();
    }

    private void searchSongBy(int loginId, int chosenSearchTypeOption) {
        String searchKeyword = "";
        List<Song> songs = new ArrayList<>();
        switch (chosenSearchTypeOption){
            case 0:
                searchKeyword = ask("Enter your title search terms");
                songs= songService.getSongsByTitle(searchKeyword);
                showSongs(loginId, songs);
                break;
            case 1:
                searchKeyword = ask("Enter your artist search terms");
                songs= songService.getSongsByArtist(searchKeyword);
                showSongs(loginId, songs);
                break;
            case 2:
                searchKeyword = ask("Enter your genre search terms");
                songs= songService.getSongsByGenre(searchKeyword);
                showSongs(loginId, songs);
                break;
            case 3:
                int searchKey = askForInt("Enter your year search ");
                songs = songService.getSongsByYear(searchKey);
                showSongs(loginId, songs);
                break;
            case 4:
                quit();
                break;

        }
    }



    private void showSongs(int loginId, List<Song> songs) {
        int count=0;
        String[] songsOptions=new String[songs.size()+1];
        for (Song song:songs) {
            songsOptions[count++]= song.getArtist() + " - " + song.getTitle();
        }
        songsOptions[count]= "RETURN MAIN MENU!!!";
        int chosenOption = askForChoice(songsOptions);
        detailsOfSongsOrExit(loginId, chosenOption,songs);
        
    }

    private void detailsOfSongsOrExit(int loginId, int chosenOption, List<Song> songs) {
        if(chosenOption==songs.size()){
            showMenu();
        }else{
            Song song= songs.get(chosenOption);
            String subtitle="Details Of Song";
            printSubheading(subtitle);

            System.out.printf("Title  : %s%nArtist : %s%nGenre  : %s%nYear   : %s%nCost   : %s%n",song.getTitle(),song.getArtist(), song.getGenre(), song.getYear(), song.getCost());
        if(loginId >0){
            askForOrder(loginId,song);
        }else{
            String reminderOrderMessage="REMINDER: You can order song if only you are log in!...";
            printSubheading(reminderOrderMessage);
        }
        }
    }

    private void askForOrder(int loginId, Song song) {
        String messageForOrder="Do you want to add Song To Cart";
        boolean addToCart= askYorN(messageForOrder);
        String messageForAmount = "Enter the amount please";
        int amount= askForInt(messageForAmount);
        createOrder((long) loginId, song, addToCart, amount);

    }

    private void createOrder(long loginId, Song song, boolean addToCart, int amount) {
        if(addToCart){
            Order order = new Order(loginId);
            long generatedIdForOrder = orderService.save(order);
            createItem(amount, generatedIdForOrder, song);

        }
    }

    private void createItem(int amount, long generatedIdForOrder, Song song) {
        Item item= new Item(generatedIdForOrder, song.getId(), amount);
        long generatedIdForItem = itemService.save(item);
        System.out.printf("Thank you for your order. %d *%s* order is in Process!!! You can follow it by OrderNumber = %d ", amount,song.getTitle(), generatedIdForOrder);
        System.out.println();
    }


    public void waitForLoading(int n) {
        for (int i = 0; i < n ; i++) {
            System.out.print(".");

            try {
                Toolkit.getDefaultToolkit().beep();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();

    }


    public void quit() {

        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(center("*** GOODBYE :) ***"));
        System.out.println(center("*** SEE YOU LATER ***"));
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.exit(0);

    }
}
