package org.osi.snacks.springmvc;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.osi.snacks.springmvc.model.TodaySnacks;
import org.osi.snacks.springmvc.model.User;
import org.springframework.web.client.RestTemplate;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8999/Snacks";
	
	
	
	/* GET */
	private static void getSnacks(){
		System.out.println("Testing getSnacks API----------");
		RestTemplate restTemplate = new RestTemplate();
		TodaySnacks todaySnacks = restTemplate.getForObject(REST_SERVICE_URI+"/snacks/1", TodaySnacks.class);
		System.out.println(todaySnacks.getSnacksName());
		System.out.println(todaySnacks.getCampus());
        System.out.println(todaySnacks.getDate());
	}
	
	private static void getTest(){
		System.out.println("Testing getSnacks API----------");
		RestTemplate restTemplate = new RestTemplate();
		String todaySnacks = restTemplate.getForObject(REST_SERVICE_URI+"/hungry/", String.class);
        System.out.println(todaySnacks);
	}
	
	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllUsers(){
		System.out.println("Testing listAllUsers API-----------");
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
		
		if(usersMap!=null){
			for(LinkedHashMap<String, Object> map : usersMap){
	            System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
	        }
		}else{
			System.out.println("No user exist----------");
		}
	}
	
	/* GET */
	private static void getUser(){
		System.out.println("Testing getUser API----------");
		RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
	}
	
	/* POST */
    private static void createUser() {
		System.out.println("Testing create User API----------");
    	RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Sarah",51,134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser() {
		System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"Chaitanya Bommaraju",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
		System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }


    /* DELETE */
    private static void deleteAllUsers() {
		System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }

    public static void main(String args[]){
    	getSnacks();
    	getTest();
		listAllUsers();
		getUser();
		createUser();
		listAllUsers();
		updateUser();
		listAllUsers();
		deleteUser();
		listAllUsers();
		deleteAllUsers();
		listAllUsers();
    }
}