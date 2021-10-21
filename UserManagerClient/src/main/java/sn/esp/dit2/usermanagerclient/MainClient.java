/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.esp.dit2.usermanagerclient;

import java.util.Scanner;
import java.util.List;

/**
 *
 */
public class MainClient {
    
    public static void main(String[] args) {
        UserServiceService userServiceService = new UserServiceService();
        UserService userService = userServiceService.getUserServicePort();
        Scanner sc = new Scanner(System.in);
        System.out.println("Login: ");
        String login = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        boolean isConnected = userService.authentication(login, password);
        if(!isConnected){
            System.out.println("Paramètres de connexion incorrects ou votre profil ne vous permet pas d'accéder au service.");
        }
        if (isConnected) {
            int opt = 0;
            do {
                System.out.println("1- Lister les utilisateurs");
                System.out.println("2- Créer un utilisateur");
                System.out.println("3- Modifier un utilisateur");
                System.out.println("4- Supprimer un utilisateur");
                System.out.println("5- Se deconnecter");
                System.out.println("Votre choix:");
                opt = sc.nextInt();
                switch (opt) {
                    case 1:
                        List<User> users = userService.getAll();
                        System.out.println("\tID\tPrénom \tNom \tLogin \tProfil");
                        System.out.println("--------------------------------------------------------------");
                        for (User user : users) {
                            System.out.println("\t"+user.getId()+"\t"+user.getPrenom()+"\t"+user.getNom()+"\t"+user.getLogin()+"\t"+user.getProfil());
                        }
                        break;
                    case 2:
                        System.out.println("Veuillez fournir les informations suivantes.");
                        sc.nextLine();
                        User user = saveUser(sc);
                        System.out.println(userService.create(user, login, password));
                        break;
                    
                    case 3:
                        System.out.println("Veuillez fournir les informations suivantes.");
                        System.out.println("ID:");
                        Long id = sc.nextLong();
                        sc.nextLine();
                        User user2 = saveUser(sc);
                        user2.setId(id);
                        System.out.println(userService.update(user2, login, password));
                        break;
                    case 4:
                         System.out.println("Veuillez fournir les informations suivantes.");
                        System.out.println("ID:");
                        Long id2 = sc.nextLong();
                        System.out.println(userService.delete(id2, login, password));
                        break;
                    case 5:
                        System.out.println(userService.deconnexion(login, password));
                        break;
                    default:
                        System.out.println("Cette option n'existe pas.");
                        break;
                }
            } while (opt != 5);
            
        }
    }
    
    public static User saveUser(Scanner sc) {
        System.out.println("Prénom:");
        String prenom = sc.nextLine();
        System.out.println("Nom:");
        String nom = sc.nextLine();
        System.out.println("Login:");
        String log = sc.nextLine();
        System.out.println("Password:");
        String pwd = sc.nextLine();
        int choice = 0;
        do {
            System.out.println("Profil: 1- ADMINISTRATEUR, 2- EDITEUR");
            choice = sc.nextInt();
        } while (choice != 1 && choice != 2);
        String profil = choice == 1 ? "ADMINISTRATEUR" : "EDITEUR";
        User user = new User();
        user.setPrenom(prenom);
        user.setNom(nom);
        user.setPassword(pwd);
        user.setLogin(log);
        user.setProfil(profil);
        return user;
    }
    
    public MainClient() {
    }
}
