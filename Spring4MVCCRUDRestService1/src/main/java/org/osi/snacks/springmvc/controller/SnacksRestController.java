package org.osi.snacks.springmvc.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.osi.snacks.springmvc.model.TodaySnacks;
import org.osi.snacks.springmvc.model.User;
import org.osi.snacks.springmvc.service.SnacksService;
import org.osi.snacks.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SnacksRestController {

	@Autowired
	UserService userService;  //Service which will do all data retrieval/manipulation work
	
	//-------------------Retrieve Snacks Today--------------------------------------------------------
	
		@RequestMapping(value = "/snacks/{campus}/{day}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TodaySnacks> getTest(@PathVariable("campus") String campus,@PathVariable("day") String day) {
			System.out.println("Fetching snacks for campus with id " + campus +" For the Day "+ day);
			TodaySnacks snacks = new SnacksService().getSnacks(campus,day);
			return new ResponseEntity<TodaySnacks>(snacks, HttpStatus.OK);
		}
	//-------------------Retrieve Snacks Today--------------------------------------------------------
	
		@RequestMapping(value = "/hungry/", method = RequestMethod.GET)
		public ResponseEntity<String> getSnacks() {
			return new ResponseEntity<String>("Hi Chaitanya", HttpStatus.OK);
		}
	
	//-------------------Retrieve All Users--------------------------------------------------------
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}


	//-------------------Retrieve Single User--------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	
	//-------------------Create a User--------------------------------------------------------
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	//------------------- Update a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);
		
		User currentUser = userService.findById(id);
		
		if (currentUser==null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());
		
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	//------------------- Delete a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	
	//------------------- Delete All User --------------------------------------------------------
	
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	 private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 50 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;
	   
	   
	 /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
    public @ResponseBody
    void uploadMultipleFileHandler(HttpServletRequest request,HttpServletResponse response) {
    	
    	
    	   isMultipart = ServletFileUpload.isMultipartContent(request);
    	   
    	   DiskFileItemFactory factory = new DiskFileItemFactory();
    	      // maximum size that will be stored in memory
    	      factory.setSizeThreshold(maxMemSize);
    	      // Location to save data that is larger than maxMemSize.
    	      factory.setRepository(new File("c:\\temp"));

    	      // Create a new file upload handler
    	      ServletFileUpload upload = new ServletFileUpload(factory);
    	      // maximum file size to be uploaded.
    	      upload.setSizeMax( maxFileSize );

    	      try{ 
    	      // Parse the request to get file items.
    	      List fileItems = upload.parseRequest(request);
    		
    	      // Process the uploaded file items
    	      Iterator i = fileItems.iterator();
  
    	      while ( i.hasNext () ) 
    	      {
    	         FileItem fi = (FileItem)i.next();
    	         if ( !fi.isFormField () )	
    	         {
    	            // Get the uploaded file parameters
    	            String fieldName = fi.getFieldName();
    	            System.out.println("fieldName >>>>>>>>>>> "+fieldName);
    	            String fileName = fi.getName();
    	            System.out.println("fileName >>>>>>>>>>>>>> "+fileName);
    	            String contentType = fi.getContentType();
    	            System.out.println("contentType >>>>>>>>> "+contentType);
    	            boolean isInMemory = fi.isInMemory();
    	            long sizeInBytes = fi.getSize();
    	            // Write the file
    	            if( fileName.lastIndexOf("\\") >= 0 ){
    	               file = new File( filePath + 
    	               fileName.substring( fileName.lastIndexOf("\\"))) ;
    	            }else{
    	               file = new File( filePath + 
    	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
    	            }
    	            fi.write( file ) ;
    	            System.out.println("Uploaded Filename: " + fileName + "<br>");
    	         }
    	      }
    	      
    	      }catch(Exception ex) {
    	    	  System.out.println(ex);
    	      }
    }

}
