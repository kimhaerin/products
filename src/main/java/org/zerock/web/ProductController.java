package org.zerock.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.ProductVO;
import org.zerock.persistence.ProductDAOImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProductController {
	@Inject
	private ProductDAOImpl dao;

	@Autowired
	private ProductVO vo;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@GetMapping("/register")
	public void register() {
		logger.info("register called.......");
	}

	@PostMapping("/registerAction")
	public String registerAction(ProductVO vo, Model model) throws Exception {

		dao.create(vo);
		logger.info(dao.list().toString());
		model.addAttribute("file", dao.list());

		return "list";
	}

	@GetMapping("/list")
	public void inputGET(Model model) throws Exception {

		logger.info("list called.......");

	}

	@PostMapping("/cartAction")

	public String cartAction(@RequestParam(value = "check", required = true) List<String> imsi, Model model) throws Exception {
		
		List<ProductVO> listImsi = new ArrayList<ProductVO>();
		logger.info("VO : " + vo);

		for (String string : imsi) {
			listImsi.add(dao.read(string)); 
		}

		model.addAttribute("listImsi", listImsi);
		
		return "cart";
	}
	

	@GetMapping(value = "/show", produces = { "image/jpg", "image/png", "image/gif" })
	public @ResponseBody byte[] show(String name) throws Exception {

		InputStream in = new FileInputStream("C:\\products\\" + name);
		// OutputStream pit = new ByteArrayOutputStream();

		return IOUtils.toByteArray(in);

	}

	@PostMapping("/uploadFile")
	@ResponseBody // 리턴값이 순수한 문자열을 나타내기 위해서 사용
	public String uploadFile(MultipartFile file) throws IOException {

		UUID uid = UUID.randomUUID();

		String fileName = file.getOriginalFilename();
		String uploadName = uid + "_" + fileName;

		FileOutputStream fos = new FileOutputStream("C:\\products\\" + uploadName);
		IOUtils.copy(file.getInputStream(), fos);

		fos.close();

		return uploadName; // 실제 파일명을 리턴

	}

	@PostMapping("/upload")
	public String upload(String id, String pw, MultipartFile profile, Model model) throws Exception {

		logger.info("id: " + id);
		logger.info("pw: " + pw);
		logger.info("profile: " + profile);

		UUID uid = UUID.randomUUID();

		String fileName = profile.getOriginalFilename();
		String uploadName = uid + "_" + fileName;

		FileOutputStream fos = new FileOutputStream("C:\\zzz\\" + uploadName);
		IOUtils.copy(profile.getInputStream(), fos);

		fos.close();

		model.addAttribute("file", uploadName);

		return "success";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

}
