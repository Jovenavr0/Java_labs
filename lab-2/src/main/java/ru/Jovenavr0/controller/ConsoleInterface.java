package ru.Jovenavr0.controller;

import ru.Jovenavr0.entity.Owner;
import ru.Jovenavr0.entity.Pet;
import ru.Jovenavr0.entity.PetColor;
import ru.Jovenavr0.service.OwnerService;
import ru.Jovenavr0.service.PetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleInterface {
    private final OwnerService ownerService;
    private final PetService petService;
    private final Scanner scanner;

    private boolean is_working;

    public ConsoleInterface(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.scanner = new Scanner(System.in);
        is_working = true;
    }

    public void start() {

        System.out.println("\nWelcome to the Owners/Pets system!");

        int user_choice;

        while(is_working){
            ShowOptions();
            user_choice = GetChoice();
            SelectionHandler(user_choice);
        }

        scanner.close();
    }

    private void ShowOptions(){
        System.out.println("1. Create an owner.");
        System.out.println("2. Create a pet.");
        System.out.println("3. Add friend to pet.");
        System.out.println("4. List of pets.");
        System.out.println("5. List of owners.");
        System.out.println("0. Exit.");
        System.out.println("Enter the action number: ");
    }

    private int GetChoice(){
        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice;
    }

    private void SelectionHandler(int user_choice){

        switch (user_choice) {
            case 1:
                CreatePetHandler();
                break;
            case 2:
                CreateOwnerHandler();
                break;
            case 3:
                AddFriendHandler();
                break;
            case 4:
                GetAllPets();
                break;
            case 5:
                GetAllOwners();
                break;
            case 0:
                FinishWork();
                break;
            default:
                System.out.println("wrong choice. Try again.");
        }
    }

    private void AddFriendHandler() {

        List<Pet> pets = petService.getAllPets();

        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getName() + ". " + pets.get(i).getId());
        }

        System.out.print("Take two number of pet for friends: ");
        int pet1 = scanner.nextInt();
        scanner.nextLine();
        int pet2 = scanner.nextInt();
        scanner.nextLine();

        UUID uuid1 = pets.get(pet1 - 1).getId();
        UUID uuid2 = pets.get(pet2 - 1).getId();

        petService.addFriend(uuid1, uuid2);
    }

    private void FinishWork(){
        is_working = false;
    }

    private void CreatePetHandler() {

        List<Owner> owners =  GetAllOwners();

        System.out.print("Take a number of owner for your pet: ");
        int ownerNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the name of the pet: ");
        String name = scanner.nextLine();
        System.out.print("Enter the breed of the pet: ");
        String breed = scanner.nextLine();
        System.out.print("Enter the color of the pet: 1 - black, 2 - white, 3 - gray, 4 - brown, 5 - spotted");
        int colorNumb = scanner.nextInt();
        PetColor color = switch (colorNumb) {
            case 1 -> PetColor.black;
            case 2 -> PetColor.white;
            case 3 -> PetColor.gray;
            case 4 -> PetColor.brown;
            case 5 -> PetColor.spotted;
            default -> PetColor.black;
        };

        Pet pet = new Pet();

        pet.SetName(name);
        pet.SetBreed(breed);
        pet.SetBirthDate(LocalDate.now());
        pet.SetOwner(owners.get(ownerNumber));
        pet.SetColor(color);

        petService.savePet(pet);
    }

    private void CreateOwnerHandler() {
        System.out.println("Start creating owner.");
        System.out.print("Enter owner name: ");
        String name = scanner.nextLine();
        Owner owner = new Owner();
        owner.setName(name);
        owner.setBirthDay(LocalDate.now());
        ownerService.saveOwner(owner);
    }

    private List<Pet> GetAllPets() {
        System.out.print("List of pets: ");
        List<Pet> pets = petService.getAllPets();
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getName() + ". " + pets.get(i).getId());
        }

        return pets;
    }

    private List<Owner> GetAllOwners() {
        System.out.print("List of owners: ");
        List<Owner> owners = ownerService.getAllOwners();
        for (int i = 0; i < owners.size(); i++) {
            System.out.println((i + 1) + ". " + owners.get(i).getName() + ". " + owners.get(i).getId());
        }
        return owners;
    }

}


