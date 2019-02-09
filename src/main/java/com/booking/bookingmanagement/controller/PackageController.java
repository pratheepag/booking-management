package com.booking.bookingmanagement.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.booking.bookingmanagement.configuration.ProfileConfiguration;
import com.booking.bookingmanagement.model.Packages;
import com.booking.bookingmanagement.service.CategoryService;
import com.booking.bookingmanagement.service.PackageService;

@Controller
public class PackageController {

	@Autowired
	private PackageService packageService;
	
	@Autowired
	private CategoryService categoryService;
		
	@Autowired
    ServletContext context;
	
	@Autowired
	private ProfileConfiguration profileConfiguration;
	
	@RequestMapping(value = "/admin/addPackage", method = RequestMethod.GET)
	public String addPackage(@ModelAttribute("packages") Packages packages, Model model, HttpSession session, BindingResult result, HttpServletRequest request) throws MalformedURLException {
		//System.out.println(commonUtility.getURLBase(request.getRequestURL().toString()));
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		model.addAttribute("categoryList", categoryService.findAllCategory());
		return "admin/addPackage";
	}
	
	
	@RequestMapping(value = "/admin/addPackage", method = RequestMethod.POST)
	//public void addPackage(@Valid @ModelAttribute("addPackage") Packages packages, MultipartFile file, Model model, BindingResult result, HttpServletResponse response, HttpServletRequest request) throws IOException {
	public String addPackage( @RequestParam("file") MultipartFile file,@Valid @ModelAttribute("packages") Packages packages, BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println(result.hasErrors());
		result.getAllErrors().forEach(System.out::println);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		if(result.hasErrors()) {
			return "admin/addPackage";
		}else {
			if (!file.getOriginalFilename().equals("")) {
				Path filePath = Paths.get(context.getRealPath("/")+File.separator+"uploads"+File.separator+file.getOriginalFilename());
	        	Files.write(filePath, file.getBytes());
			}
		 	packages.setImage(file.getOriginalFilename());
			model.addAttribute("base_url", profileConfiguration.getBaseUrl());
			packageService.save(packages);
			response.sendRedirect("/admin/listPackages");
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/deletePackage/{id}", method = RequestMethod.GET)
	public void deletePackage(@PathVariable("id") Long id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		packageService.delete(id);
		
		response.sendRedirect("/admin/listPackages");
	}
	
	@RequestMapping(value = "/admin/listPackages", method = RequestMethod.GET)
	public  String listPackages(@PageableDefault(size = 1, sort = "id") Pageable pageable, Model model, HttpSession session) {
		Page<Packages> page =  packageService.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		return "/admin/listPack";
	}
	
	@RequestMapping(value = "/admin/editPackage/{id}", method = RequestMethod.GET)
	public String editPackage(@PathVariable("id") Long id, @ModelAttribute("packages") Optional<Packages> packages, Model model, HttpSession session) {
		packages = packageService.findPackage(id);
		//System.out.println(packages);
		//model.addAttribute("packageImage", packages.image);
		model.addAttribute("packages", packages);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		model.addAttribute("categoryList", categoryService.findAllCategory());
		return "/admin/editPackage";
	}
	
	@RequestMapping(value = "/admin/editPackage", method = RequestMethod.POST)
	public String editPackage(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file,@ModelAttribute("packages") Packages packages, Model model, HttpSession session, BindingResult result, HttpServletResponse response) throws IOException {
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		model.addAttribute("categoryList", categoryService.findAllCategory());
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "/admin/editPackage/"+id;
		}else {
			if (!file.getOriginalFilename().equals("")) {
				Path filePath = Paths.get(context.getRealPath("/")+File.separator+"uploads"+File.separator+file.getOriginalFilename());
	        	Files.write(filePath, file.getBytes());
	        	packages.setImage(file.getOriginalFilename());
			}else {
				Optional<Packages> packagesById = packageService.findPackage(id);
				packages.setImage(packagesById.get().getImage());
			}
		 	
			packageService.save(packages);
			model.addAttribute("base_url", profileConfiguration.getBaseUrl());
			response.sendRedirect("/admin/listPackages");
		}
		return null;
	}
	
	/*@RequestMapping(value = "image/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

		File serverFile = new File("C:\\Users\amoghsri\\Documents\\workspacespring\\booking-management\\src\\main\\webapp\\uploads\\" + imageName + ".jpg");

	    return Files.readAllBytes(serverFile.toPath());	
	} */
	
}
