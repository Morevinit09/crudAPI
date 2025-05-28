
package com.example.demo.serviceimpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Pagination.SearchFilter;
import com.example.demo.Pagination.UserPagination;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.ErrorTable;
import com.example.demo.entity.QueuTable;
import com.example.demo.entity.UserEntity;
import com.example.demo.enums.Gender;
import com.example.demo.enums.Title;
import com.example.demo.repository.ErrorTableRepository;
import com.example.demo.repository.QueuRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private QueuRepository queuRepository;

	@Autowired
	private ErrorTableRepository errorTableRepository;

	// @Autowired
	// private UserEntity entity;

	@Autowired
	private EntityManager entityManager;

	/*
	 * private QueuTable queuTable; private Row headerRow2; private List<UserEntity>
	 * errorsFields; private String status;
	 */

	@Override
	public String addUserInfo(UserDto userdto) {

		UserEntity entity = new UserEntity();

		// Full Name Validation and Mapping
		String fullName = userdto.getUserFullName();
		if (fullName == null || fullName.trim().isEmpty()) {
			throw new IllegalArgumentException("Please Enter Name");
		}
		entity.setUserfullName(fullName);

		// Email Validation and Mapping
		String email = userdto.getEmail();
		if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Please enter valid email");
		}
		entity.setEmail(email);

		// Mobile Number Validation and Mapping
		// String mobileNo = userdto.getMobileNumber();
		// if (mobileNo == null ) {
		// throw new IllegalArgumentException("Please enter valid mobile number");
		// }
		// entity.setMobileNumber(mobileNo);

		// Annual Income Validation and Mapping
		Long annualIncome = userdto.getAnnualincome();

		entity.setAnnualincome(annualIncome);

		// User Address Mapping
		String userAddress = userdto.getUseraddress();
		// if (userAddress == null) {
		// throw new IllegalArgumentException("Please enter a valid address");
		// }
		entity.setUserAddress(userAddress);

		// Address Line 1 Mapping
		String address1 = userdto.getUseraddress1();
		entity.setUseraddress1(address1);

		// Address Line 2 Mapping
		String address2 = userdto.getUseraddress2();
		entity.setUseraddress2(address2);

		// Address Line 3 Mapping
		String address3 = userdto.getUseraddress3();
		entity.setUseraddress3(address3);

		// City Validation and Mapping
		String city = userdto.getUserCity();
		// if (city == null || !city.isEmpty()) {
		// throw new IllegalArgumentException("Enter City");
		// }
		entity.setUsercity(city);

		// PAN Number Validation and Mapping
		String pannumber = userdto.getPannumber();
		String panString = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
		if (pannumber == null || !pannumber.matches(panString)) {
			throw new IllegalArgumentException("Invalid PAN number");
		}
		entity.setPannumber(pannumber);

		// Aadhar Number Validation and Mapping
		String aadharNo = userdto.getAadharnumber();
		if (aadharNo == null || !aadharNo.matches("^[2-9]{1}[0-9]{11}$")) {
			throw new IllegalArgumentException("Invalid Aadhar Number");
		}
		entity.setAadharnumber(aadharNo);

		Long mobileno = userdto.getMobileNumber();
		if (mobileno != null && !mobileno.toString().isEmpty()) {
			entity.setMobileNumber(mobileno);
		}

		// Alternate Mobile Number Mapping
		Long alternatemobileno = userdto.getAlternatemobileno();
		if (alternatemobileno != null && !alternatemobileno.toString().isEmpty()) {
			entity.setAlternatemobileno(alternatemobileno);
		}

		// GENDER Validation and Mapping
		com.example.demo.enums.Gender gender = userdto.getGender();
		if (gender == null) {
			throw new IllegalArgumentException("Enter gender");
		}
		entity.setGender(gender);

		// Title Validation and Mapping
		Title title = userdto.getTitle();
		if (title == null) {
			throw new IllegalArgumentException("Enter title");
		}
		entity.setTitle(title);

		Date dob = userdto.getUserDob(); // This is already a Date, so no need to parse it
		if (dob != null) {
			entity.setUserDob(dob); // Set the date
		} else {
			throw new IllegalArgumentException("Please enter valid date of birth");
		}

		// State Validation and Mapping
		String state = userdto.getUserState();
		// if (state == null || state.isEmpty()) {
		// throw new IllegalArgumentException("Enter State");
		// }
		entity.setUserstate(state);
		entity.setUserstatus('Y');

		// Save to Database
		UserEntity save = repository.save(entity);
		Character userStatus = save.getUserstatus();
		System.out.println(" userStatus   " + userStatus);
		return "User Added Successfully";
	}

//	=================================================================================================================================

	@Override
	public String update(Long userId, UserDto userdto) {
		Optional<UserEntity> optionalUser = repository.findById(userId);

		if (optionalUser.isPresent()) {
			UserEntity existingEntity = optionalUser.get();

			// Full Name
			String fullName = userdto.getUserFullName();
			if (fullName != null && !fullName.trim().isEmpty()) {
				existingEntity.setUserfullName(fullName);
			}

			// Email
			String email = userdto.getEmail();
			if (email != null) {
				if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
					existingEntity.setEmail(email);
				} else {
					throw new IllegalArgumentException("Invalid email format");
				}
			}

			// Mobile Number
			String mobileNumber = userdto.getMobileNumber().toString();
			if (mobileNumber != null) {
				if (mobileNumber.matches("^[6-9]\\d{9}$")) {
					existingEntity.setMobileNumber(userdto.getMobileNumber());
				} else {
					throw new IllegalArgumentException("Invalid mobile number format");
				}
			}

			// Annual Income
			Long annualIncome = userdto.getAnnualincome();
			if (annualIncome != null) {
				if (annualIncome >= 0) {
					existingEntity.setAnnualincome(annualIncome);
				} else {
					throw new IllegalArgumentException("Annual income must be non-negative");
				}
			}

			// User Address
			String useraddress = userdto.getUseraddress();
			if (useraddress != null && !useraddress.trim().isEmpty()) {
				existingEntity.setUserAddress(useraddress);
			}

			// Address lines
			String useraddress1 = userdto.getUseraddress1();
			if (userdto.getUseraddress1() != null) {
				existingEntity.setUseraddress1(useraddress1);
			}
			String useraddress2 = userdto.getUseraddress2();
			if (userdto.getUseraddress2() != null) {
				existingEntity.setUseraddress2(useraddress2);
			}
			String useraddress3 = userdto.getUseraddress3();
			if (userdto.getUseraddress3() != null) {
				existingEntity.setUseraddress3(useraddress3);
			}

			// City
			String city = userdto.getUserCity();
			if (city != null && !city.trim().isEmpty()) {
				existingEntity.setUsercity(city);
			}

			// PAN
			String pannumber = userdto.getPannumber();
			if (pannumber != null) {
				if (pannumber.matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$")) {
					existingEntity.setPannumber(pannumber);
				} else {
					throw new IllegalArgumentException("Invalid PAN format");
				}
			}

			// String aadharNumber = userdto.getAadharNumber();
			// if (aadharNumber != null) {
			// if (aadharNumber.matches("^[2-9]{1}[0-9]{11}$")) {
			// existingEntity.setAadharnumber(aadharNumber);
			// } else {
			// throw new IllegalArgumentException("Invalid Aadhar number");
			// }
			// }

			// Alternate Mobile
			String altMobile = userdto.getAlternatemobileno().toString();
			if (altMobile != null) {
				if (altMobile.matches("^[6-9]\\d{9}$")) {
					existingEntity.setAlternatemobileno(userdto.getAlternatemobileno());
				} else {
					throw new IllegalArgumentException("Invalid alternate mobile number");
				}
			}

			// GENDER Validation and Mapping
			com.example.demo.enums.Gender gender = userdto.getGender();
			if (gender == null) {
				throw new IllegalArgumentException("Enter gender");
			}
			existingEntity.setGender(gender);

			// Title
			Title title = userdto.getTitle();
			if (title != null) {
				existingEntity.setTitle(title);
			}

			// Date of Birth
			Date dob = userdto.getUserDob();
			if (dob != null) {
				existingEntity.setUserDob(dob);
			}

			// State
			String state = userdto.getUserState();
			if (state != null && !state.trim().isEmpty()) {
				existingEntity.setUserstate(state);
			}

			// Save updated entity
			repository.save(existingEntity);
			return "User updated successfully";
		} else {
			return "User with ID " + userId + " not found";
		}
	}

	@Override
	public Void deleteUserById(Long userId) {
		Optional<UserEntity> findbyuserid = repository.findById(userId);

		if (findbyuserid.isPresent()) {
			// datatype variable = code

			UserEntity userEntity = findbyuserid.get();

			userEntity.setUserstatus('N');
			repository.save(userEntity);
		}
		return null;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto userFindById(Long userId) {
		Optional<UserEntity> userOpt = repository.findById(userId);
		UserDto dto = new UserDto();

		if (userOpt.isPresent()) {
			UserEntity details = userOpt.get();

			dto.setUserFullName(details.getUserfullName());
			dto.setUseraddress(details.getUseraddress());
			dto.setUseraddress1(details.getUseraddress1());
			// dto.setUseraddress2(details.getUseraddress2());
			// dto.setUseraddress3(details.getUseraddress3());
			dto.setAlternatemobileno(details.getAlternatemobileno());
			dto.setUserCity(details.getUsercity());
			dto.setEmail(details.getEmail());
			dto.setMobileNumber(details.getMobileNumber());
			dto.setUserState(details.getUserstate());
			dto.setGender(details.getGender());
			dto.setUserDob(details.getUserDob());
			// dto.setAadharNumber(details.getAadharnumber());
			dto.setPannumber(details.getPannumber());
			// dto.setUserstatus(details.getUserstatus());
			dto.setAnnualincome(details.getAnnualincome());
			dto.setTitle(details.getTitle());
		}

		return dto;

	}

//	========================================================================================================================

	@Override
	public List<Map<String, Object>> findAllWithPagination(UserPagination pagination) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> root = cq.from(UserEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		List<SearchFilter> searchFilters = pagination.getSearchFilter();

		// Apply filters (same as before)
		if (searchFilters != null && !searchFilters.isEmpty()) {
			for (SearchFilter searchFilter : searchFilters) {
				String userfullName = searchFilter.getUserfullName();
				String mobileNumber = searchFilter.getMobileNumber();
				String email = searchFilter.getEmail();
				String usercity = searchFilter.getUsercity();
				String userstate = searchFilter.getUserstate();
				String aadharnumber = searchFilter.getAadharnumber();

				if (userfullName != null && !userfullName.trim().isEmpty()) {
					predicates.add(cb.like(cb.lower(root.get("userfullName")), "%" + userfullName.toLowerCase() + "%"));
				}
				if (mobileNumber != null && !mobileNumber.trim().isEmpty()) {
					predicates.add(cb.equal(cb.lower(root.get("mobileNumber")), mobileNumber.toLowerCase()));
				}
				if (email != null && !email.trim().isEmpty()) {
					predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
				}
				if (usercity != null && !usercity.trim().isEmpty()) {
					predicates.add(cb.like(cb.lower(root.get("usercity")), "%" + usercity.toLowerCase() + "%"));
				}
				if (userstate != null && !userstate.trim().isEmpty()) {
					predicates.add(cb.like(cb.lower(root.get("userstate")), "%" + userstate.toLowerCase() + "%"));
				}
				if (aadharnumber != null && !aadharnumber.trim().isEmpty()) {
					predicates.add(cb.like(cb.lower(root.get("aadharnumber")), "%" + aadharnumber.toLowerCase() + "%"));
				}
			}
		}

		// Apply status filter
		predicates.add(cb.equal(root.get("userstatus"), 'Y'));

		// Add predicates to the WHERE clause
		cq.where(cb.and(predicates.toArray(new Predicate[0])));

		// Sorting logic
		String sortBy = (pagination.getSortBy() != null && !pagination.getSortBy().trim().isEmpty())
				? pagination.getSortBy()
				: "userId";

		String sortOrder = (pagination.getSortOrder() != null && !pagination.getSortOrder().trim().isEmpty())
				? pagination.getSortOrder()
				: "desc";

		if ("desc".equalsIgnoreCase(sortOrder)) {
			cq.orderBy(cb.desc(root.get(sortBy)));
		} else {
			cq.orderBy(cb.asc(root.get(sortBy)));
		}

		// Create query
		TypedQuery<UserEntity> query = entityManager.createQuery(cq);

		// Pagination logic
		Integer page = pagination.getPage();
		Integer size = pagination.getSize();

		if ((page == null || page == 0) && (size == null || size == 0)) {
			return query.getResultList().stream().map(this::convertToMap).collect(Collectors.toList());
		}

		if (page == null || size == null || page <= 0 || size <= 0) {
			throw new IllegalArgumentException("Page and size must be greater than or equal to 0.");
		}

		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);

		// Directly convert to List<Map<String, Object>>
		if (query.getResultList().stream().map(this::convertToMap).collect(Collectors.toList()).isEmpty()) {
			throw new IllegalArgumentException("User not found");
		}

		return query.getResultList().stream().map(this::convertToMap).collect(Collectors.toList());
	}

	// Map all fields
	private Map<String, Object> convertToMap(UserEntity userEntity) {

		Map<String, Object> map = new HashMap<>();
		map.put("userId", userEntity.getUserId());
		map.put("userfullName", userEntity.getUserfullName());
		map.put("email", userEntity.getEmail());
		map.put("mobileNumber", userEntity.getMobileNumber());
		map.put("alternatemobileno", userEntity.getAlternatemobileno());
		map.put("annualincome", userEntity.getAnnualincome());
		map.put("pannumber", userEntity.getPannumber());
		map.put("aadharnumber", userEntity.getAadharnumber());
		map.put("gender", userEntity.getGender());
		map.put("title", userEntity.getTitle());
		map.put("useraddress", userEntity.getUseraddress());
		map.put("useraddress1", userEntity.getUseraddress1());
		map.put("useraddress2", userEntity.getUseraddress2());
		map.put("useraddress3", userEntity.getUseraddress3());
		map.put("userdob", userEntity.getUserdob());
		map.put("usercity", userEntity.getUsercity());
		map.put("userstate", userEntity.getUserstate());
		map.put("userstatus", userEntity.getUserstatus());
		return map;
	}

//===================================================================================================================================
	@Override
	public String generateExcelFile() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sample usersheet");
		XSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("userfullName");
		row.createCell(1).setCellValue("email");
		row.createCell(2).setCellValue("mobileNumber");
		row.createCell(3).setCellValue("alternatemobileno");
		row.createCell(4).setCellValue("annualincome");
		row.createCell(5).setCellValue("pannumber");
		row.createCell(6).setCellValue("aadharnumber");
		row.createCell(7).setCellValue("gender");
		row.createCell(8).setCellValue("title");
		row.createCell(9).setCellValue("useraddress");
		row.createCell(10).setCellValue("useraddress1");
		row.createCell(11).setCellValue("useraddress2");
		row.createCell(12).setCellValue("useraddress3");
		row.createCell(13).setCellValue("userdob");
		row.createCell(14).setCellValue("usercity");
		row.createCell(15).setCellValue("userstate");

		String fileName = "Sample_";
		String filePath = "C:/SmpleGenerateFile/";

		String randomName = UUID.randomUUID().toString().substring(0, 4);

		String fullFilePathString = filePath + fileName + randomName + ".xlsx";

		FileOutputStream fOutputStream = new FileOutputStream(fullFilePathString);

		workbook.write(fOutputStream);
		workbook.close();

		return "Your File Downloaded on this Location:: " + fullFilePathString;

	}
//	==============================================================Import Export Excel =======================================================================================

	@Override
	public String saveDataFromExcelFile(MultipartFile file) throws IOException {
	    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
	    XSSFSheet sheet = workbook.getSheetAt(0);

	    Row headerRow = sheet.getRow(0);
	    int lastCol = headerRow.getLastCellNum();
	    headerRow.createCell(lastCol).setCellValue("Error Message");
	    headerRow.createCell(lastCol + 1).setCellValue("Error Status");

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        XSSFRow row = sheet.getRow(i);
	        if (row == null) continue;

	        List<String> errorList = new ArrayList<>();
	        List<String> errorFiled = new ArrayList<>();
	        UserEntity user = new UserEntity();

	        if (row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) {
	            errorList.add("UserFullName is Empty");
	            errorFiled.add("UserFullName");
	        }

	        if (row.getCell(1) == null || row.getCell(1).getStringCellValue().isEmpty()) {
	            errorList.add("Email is Empty");
	            errorFiled.add("Email");
	        }

	        if (row.getCell(2) == null || row.getCell(2).getCellType() != CellType.NUMERIC) {
	            errorList.add("Mobile number is empty or invalid");
	            errorFiled.add("Mobile");
	        }

	        if (row.getCell(3) == null || row.getCell(3).getCellType() != CellType.NUMERIC) {
	            errorList.add("Alternate Mobile Number is empty or invalid");
	            errorFiled.add("Alternate Mobile Number");
	        }

	        if (row.getCell(4) == null || row.getCell(4).getCellType() != CellType.NUMERIC) {
	            errorList.add("AnnualIncome is Empty");
	            errorFiled.add("Annual Income");
	        }

	        if (row.getCell(5) == null || row.getCell(5).getStringCellValue().isEmpty()
	                || !row.getCell(5).getStringCellValue().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
	            errorList.add("Pannumber is Empty or Invalid Format");
	            errorFiled.add("Pannumber");
	        }

	        if (row.getCell(6) == null || row.getCell(6).getCellType() != CellType.NUMERIC) {
	            errorList.add("Aadhaarnumber is Empty or Invalid");
	            errorFiled.add("Aadhaarnumber");
	        }

	        try {
	            if (row.getCell(7) == null || Gender.valueOf(row.getCell(7).getStringCellValue()) == null) {
	                errorList.add("Invalid or Empty Gender");
	                errorFiled.add("Gender");
	            }
	        } catch (Exception e) {
	            errorList.add("Invalid or Empty Gender");
	            errorFiled.add("Gender");
	        }

	        try {
	            if (row.getCell(8) == null || Title.valueOf(row.getCell(8).getStringCellValue()) == null) {
	                errorList.add("Invalid or Empty Title");
	                errorFiled.add("Title");
	            }
	        } catch (Exception e) {
	            errorList.add("Invalid or Empty Title");
	            errorFiled.add("Title");
	        }

	        if (row.getCell(9) == null || row.getCell(9).getStringCellValue().isEmpty()) {
	            errorList.add("Address is Empty");
	            errorFiled.add("Address");
	        }

	        if (row.getCell(10) == null || row.getCell(10).getStringCellValue().isEmpty()) {
	            errorList.add("Address1 is Empty");
	            errorFiled.add("Address1");
	        }

	        if (row.getCell(11) == null || row.getCell(11).getStringCellValue().isEmpty()) {
	            errorList.add("Address2 is Empty");
	            errorFiled.add("Address2");
	        }

	        if (row.getCell(12) == null || row.getCell(12).getStringCellValue().isEmpty()) {
	            errorList.add("Address3 is Empty");
	            errorFiled.add("Address3");
	        }

	        if (row.getCell(13) == null || row.getCell(13).getCellType() != CellType.NUMERIC) {
	            errorList.add("Dob is Empty or Invalid");
	            errorFiled.add("Dob");
	        }

	        if (row.getCell(14) == null || row.getCell(14).getStringCellValue().isEmpty()) {
	            errorList.add("City is Empty");
	            errorFiled.add("City");
	        }

	        if (row.getCell(15) == null || row.getCell(15).getStringCellValue().isEmpty()) {
	            errorList.add("State is Empty");
	            errorFiled.add("State");
	        }

	        if (row.getCell(16) == null || row.getCell(16).getStringCellValue().isEmpty()) {
	            errorList.add("Status is Empty");
	            errorFiled.add("Status");
	        }

	        if (!errorList.isEmpty()) {
	            for (int k = 0; k < errorList.size(); k++) {
	                ErrorTable errorTable = new ErrorTable();
	                errorTable.setErrorMessage(errorList.get(k));
	                errorTable.setErrorField(errorFiled.get(k));
	                errorTable.setErrorStatus('F');
	                errorTable.setErrorRow("Row " + i);
	                errorTableRepository.save(errorTable);
	            }

	            row.createCell(lastCol).setCellValue(String.join(", ", errorList));
	            row.createCell(lastCol + 1).setCellValue("Failed");
	        } else {
	            user.setUserfullName(row.getCell(0).getStringCellValue());
	            user.setEmail(row.getCell(1).getStringCellValue());
	            user.setMobileNumber((long) row.getCell(2).getNumericCellValue());
	            user.setAlternatemobileno((long) row.getCell(3).getNumericCellValue());
	            user.setAnnualincome((long) row.getCell(4).getNumericCellValue());
	            user.setPannumber(row.getCell(5).getStringCellValue());
	            user.setAadharnumber(String.valueOf((long) row.getCell(6).getNumericCellValue()));
	            user.setGender(Gender.valueOf(row.getCell(7).getStringCellValue()));
	            user.setTitle(Title.valueOf(row.getCell(8).getStringCellValue()));
	            user.setUserAddress(row.getCell(9).getStringCellValue());
	            user.setUseraddress1(row.getCell(10).getStringCellValue());
	            user.setUseraddress2(row.getCell(11).getStringCellValue());
	            user.setUseraddress3(row.getCell(12).getStringCellValue());
	            user.setUserDob(row.getCell(13).getDateCellValue());
	            user.setUsercity(row.getCell(14).getStringCellValue());
	            user.setUserstate(row.getCell(15).getStringCellValue());
	            user.setUserstatus(row.getCell(16).getStringCellValue().charAt(0));

	            UserEntity save = repository.save(user);

	            ErrorTable successLog = new ErrorTable();
	            successLog.setErrorMessage("No Errors");
	            successLog.setErrorField("");
	            successLog.setErrorStatus('S');
	            successLog.setErrorRow("Row " + i);
	            ErrorTable saved = errorTableRepository.save(successLog);

	            row.createCell(lastCol).setCellValue("Success ID: " + saved.getErrorId());
	            row.createCell(lastCol + 1).setCellValue("Success");
	        }
	    }

	    // Write the updated workbook to file
	    String folderPaths = "C:\\Users\\HP\\Downloads\\";
	    String shortUUID = UUID.randomUUID().toString().substring(0, 8);
	    String filePaths = folderPaths + shortUUID + "_Proposals.xlsx";

	    try (FileOutputStream outs = new FileOutputStream(filePaths)) {
	        workbook.write(outs);
	    }

	    workbook.close();
	    return "Added Successfully";
	}

//	===============================================   Excel batchProcessing =================================================================================================================

	@Override
	public String excelBatchProcessingbatchprocessing(MultipartFile file) throws IOException {

		XSSFWorkbook workbookz = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbookz.getSheetAt(0);

		int totalrow = sheet.getLastRowNum();
		System.out.println(totalrow + "tukus6tdkfikury");
		int batchsize = 5;
		
		System.out.println("vai >>>>>>>>>>>>>>>>>>>>"+totalrow);


		if (totalrow > 4) {
			
			System.out.println("Abhishek >>>>>>>>>>>>>>>>>>>>"+totalrow);

			String folderPaths = "C:\\Users\\HP\\Downloads\\";

			String shortUUID = "xyz_" + UUID.randomUUID().toString().substring(0, 8);

			String filePaths = folderPaths + shortUUID + "Proposals.xlsx";

			FileOutputStream outs = new FileOutputStream(filePaths);
			workbookz.write(outs);

			QueuTable queTable = new QueuTable();

			queTable.setFilePath(filePaths);
			queTable.setRowCount(totalrow);
			queTable.setIsProcess("N");
			queTable.setRowRead(0);
			queTable.setStatus("N");
			queTable.setLastProcessCount(batchsize);

			queuRepository.save(queTable);

	
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null)
				continue;

			List<String> errors = new ArrayList<>();
			List<String> errorsField = new ArrayList<>();

			UserEntity userEntity = new UserEntity();

			if (row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) {
				errors.add("UserFullName is Empty");
				errorsField.add("FullName");
			}
			if (row.getCell(1) == null || row.getCell(1).getStringCellValue().isEmpty()) {
				errors.add("Email is Empty");
				errorsField.add("Email");
			}

			Long number1 = (long) row.getCell(2).getNumericCellValue();

			if (row.getCell(2) == null || row.getCell(2).getCellType() != CellType.NUMERIC
					|| row.getCell(2).getNumericCellValue() == 0) {
				errors.add("Mobile number is empty or invalid");
				errorsField.add("Mobile number");
			}

			if (row.getCell(3) == null || row.getCell(3).getCellType() != CellType.NUMERIC
					|| row.getCell(3).getNumericCellValue() == 0) {
				errors.add("Alternate Mobile Number is empty or invalid");
				errorsField.add("Alternate Mobile Number");
			}

			Long addhar = (long) row.getCell(4).getNumericCellValue();

			if (row.getCell(4) == null || row.getCell(4).getCellType() != CellType.NUMERIC) {
				errors.add("AnnualIncome is Empty");
				errorsField.add("AnnualIncome");
			}

			if (row.getCell(5) == null || row.getCell(5).getStringCellValue().isEmpty()
					|| !row.getCell(5).getStringCellValue().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
				errors.add("Pannumber is Empty or Invalid Format");
				errorsField.add("Pannumber");
			}

			Long addharnumber = (long) row.getCell(6).getNumericCellValue();

			if (row.getCell(6) == null || row.getCell(6).getCellType() != CellType.NUMERIC) {
				errors.add("Aadhaarnumber is Empty");
				errorsField.add("Aadhaarnumber");
			}

			if (Gender.valueOf(row.getCell(7).getStringCellValue()) == null
					|| Gender.valueOf(row.getCell(7).getStringCellValue()).toString().isEmpty()) {
				errors.add("Invalid or Empty Gender");
				errorsField.add("Gender");
			}

			if (Title.valueOf(row.getCell(8).getStringCellValue()) == null
					|| Title.valueOf(row.getCell(8).getStringCellValue()).toString().isEmpty()) {
				errors.add("Invalid or Empty Title");
				errorsField.add("Title");
			}

			if (row.getCell(9) == null || row.getCell(9).getStringCellValue().isEmpty()) {
				errors.add("Address is Empty");
				errorsField.add("Address");
			}

			if (row.getCell(10) == null || row.getCell(10).getStringCellValue().isEmpty()) {
				errors.add("Address1 is Empty");
				errorsField.add("Address1");
			}

			if (row.getCell(11) == null || row.getCell(11).getStringCellValue().isEmpty()) {
				errors.add("Address2 is Empty");
				errorsField.add("Address2");
			}

			if (row.getCell(12) == null || row.getCell(12).getStringCellValue().isEmpty()) {
				errors.add("Address3 is Empty");
				errorsField.add("Address3");
			}

			if (row.getCell(13) == null || row.getCell(13).getCellType() != CellType.NUMERIC) {
				errors.add("Dob is Empty");
				errorsField.add("DateOfBirth");
			}

			if (row.getCell(14) == null || row.getCell(14).getStringCellValue().isEmpty()) {
				errors.add("City is Empty");
				errorsField.add("City");
			}

			if (row.getCell(15) == null || row.getCell(15).getStringCellValue().isEmpty()) {
				errors.add("State is Empty");
				errorsField.add("State");
			}
			if (row.getCell(16) == null || row.getCell(16).getStringCellValue().isEmpty()) {
				errors.add("Status is Empty");
				errorsField.add("Status");
			}

			if (!errors.isEmpty()) {
				int k = 0;
				for (String eros : errors) {

					ErrorTable errorTable = new ErrorTable();
					errorTable.setErrorMessage(eros);
					errorTable.setErrorField(errorsField.get(k));
					errorTable.setErrorStatus('N');
					errorTable.setErrorRow("Row " + i);
					errorTableRepository.save(errorTable);

					k++;
				}
			}

			else {
				userEntity.setUserfullName(row.getCell(0).getStringCellValue());
				userEntity.setEmail(row.getCell(1).getStringCellValue());
				userEntity.setMobileNumber((long) row.getCell(2).getNumericCellValue());
				userEntity.setAlternatemobileno((long) row.getCell(3).getNumericCellValue());
				userEntity.setAnnualincome((long) row.getCell(4).getNumericCellValue());
				userEntity.setPannumber(row.getCell(5).getStringCellValue());
				userEntity.setAadharnumber(row.getCell(6).getStringCellValue());
				userEntity.setGender(Gender.valueOf(row.getCell(7).getStringCellValue()));
				userEntity.setTitle(Title.valueOf(row.getCell(8).getStringCellValue()));
				userEntity.setUserAddress(row.getCell(9).getStringCellValue());
				userEntity.setUseraddress1(row.getCell(10).getStringCellValue());
				userEntity.setUseraddress2(row.getCell(11).getStringCellValue());
				userEntity.setUseraddress3(row.getCell(12).getStringCellValue());
				userEntity.setUserDob(row.getCell(13).getDateCellValue());
				userEntity.setUsercity(row.getCell(14).getStringCellValue());
				userEntity.setUserstate(row.getCell(15).getStringCellValue());

				UserEntity save = repository.save(userEntity);

				ErrorTable errorTable = new ErrorTable();
				errorTable.setErrorField("");
				errorTable.setErrorStatus('S');
				errorTable.setErrorRow("Row " + i);
				ErrorTable save2 = errorTableRepository.save(errorTable);

			}
		}

		String folderPaths = "C:\\Users\\HP\\Downloads\\";

		String shortUUID = UUID.randomUUID().toString().substring(0, 8);

		String filePaths = folderPaths + shortUUID + "Proposals.xlsx";

		FileOutputStream outs = new FileOutputStream(filePaths);

		workbookz.write(outs);

		outs.close();

		workbookz.close();

		return "Added Successfully";
	}
//===========================================================================================================================================================

	@Scheduled(fixedDelay = 5000)
	@Override
	public void processQueuedFiles() {
	    List<QueuTable> batchques = queuRepository.findByIsProcess("N");

	    for (QueuTable queuTable : batchques) {
	        String filePath = queuTable.getFilePath();

	        try (FileInputStream fileInputStream = new FileInputStream(filePath);
	             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

	            XSSFSheet sheet = workbook.getSheetAt(0);

	            int rowStart = queuTable.getRowRead() + 1;
	            int totalrow = queuTable.getRowCount();
	            int queuSize = 3;

	            Row headerRow = sheet.getRow(0);

	            if (rowStart == 1) {
	                int lastCol = headerRow.getLastCellNum();
	                headerRow.createCell(lastCol).setCellValue("error message");
	                headerRow.createCell(lastCol + 1).setCellValue("error status");
	            }

	            for (int i = rowStart; i <= totalrow && i < rowStart + queuSize; i++) {
	                Row row = sheet.getRow(i);
	                if (row == null) continue;

	                List<String> errors = new ArrayList<>();
	                List<String> errorFields = new ArrayList<>();
	                UserEntity userEntity = new UserEntity();

	                // Validation
	                if (row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) {
	                    errors.add("FullName is Empty");
	                    errorFields.add("UserFullName");
	                }

	                if (row.getCell(1) == null || row.getCell(1).getStringCellValue().isEmpty()) {
	                    errors.add("Email is Empty");
	                    errorFields.add("Email");
	                }

	                Long mobileNumber = getCellLongValue(row.getCell(2));
	                if (mobileNumber == null) {
	                    errors.add("Mobile number is empty or invalid");
	                    errorFields.add("Mobile number");
	                }

	                Long alternatemobileno = getCellLongValue(row.getCell(3));
	                if (alternatemobileno == null) {
	                    errors.add("alternatemobileno number is empty or invalid");
	                    errorFields.add("alternatemobileno number");
	                }


	                Long annualIncome = getCellLongValue(row.getCell(4));
	                if (annualIncome == null) {
	                    errors.add("AnnualIncome is Empty or Invalid");
	                    errorFields.add("AnnualIncome");
	                }


	                if (row.getCell(5) == null || row.getCell(5).getStringCellValue().isEmpty()
	                        || !row.getCell(5).getStringCellValue().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
	                    errors.add("Pannumber is Empty or Invalid Format");
	                    errorFields.add("Pannumber");
	                }

	                if (row.getCell(6) == null || row.getCell(6).getCellType() != CellType.NUMERIC) {
	                    errors.add("Aadhaarnumber is Empty");
	                    errorFields.add("Aadhaarnumber");
	                }

	                try {
	                    if (row.getCell(7) == null || Gender.valueOf(row.getCell(7).getStringCellValue()) == null) {
	                        errors.add("Invalid or Empty Gender");
	                        errorFields.add("Gender");
	                    }
	                } catch (Exception e) {
	                    errors.add("Invalid or Empty Gender");
	                    errorFields.add("Gender");
	                }

	                try {
	                    if (row.getCell(8) == null || Title.valueOf(row.getCell(8).getStringCellValue()) == null) {
	                        errors.add("Invalid or Empty Title");
	                        errorFields.add("Title");
	                    }
	                } catch (Exception e) {
	                    errors.add("Invalid or Empty Title");
	                    errorFields.add("Title");
	                }

	                if (row.getCell(9) == null || row.getCell(9).getStringCellValue().isEmpty()) {
	                    errors.add("Address is Empty");
	                    errorFields.add("Address");
	                }

	                if (row.getCell(10) == null || row.getCell(10).getStringCellValue().isEmpty()) {
	                    errors.add("Address1 is Empty");
	                    errorFields.add("Address1");
	                }

	                if (row.getCell(11) == null || row.getCell(11).getStringCellValue().isEmpty()) {
	                    errors.add("Address2 is Empty");
	                    errorFields.add("Address2");
	                }

	                if (row.getCell(12) == null || row.getCell(12).getStringCellValue().isEmpty()) {
	                    errors.add("Address3 is Empty");
	                    errorFields.add("Address3");
	                }

	                if (row.getCell(13) == null || row.getCell(13).getCellType() != CellType.NUMERIC) {
	                    errors.add("DateOfBirth is Empty");
	                    errorFields.add("Dob");
	                }

	                if (row.getCell(14) == null || row.getCell(14).getStringCellValue().isEmpty()) {
	                    errors.add("City name is Empty");
	                    errorFields.add("City");
	                }

	                if (row.getCell(15) == null || row.getCell(15).getStringCellValue().isEmpty()) {
	                    errors.add("State is Empty");
	                    errorFields.add("State");
	                }

	                // Writing error or saving user
	                int lastCol = headerRow.getLastCellNum();
	                Cell errorMessage = row.createCell(lastCol - 2);
	                Cell errorStatus = row.createCell(lastCol - 1);

	                if (!errors.isEmpty()) {
	                    for (int k = 0; k < errors.size(); k++) {
	                        ErrorTable errorTable = new ErrorTable();
	                        errorTable.setErrorMessage(errors.get(k));
	                        errorTable.setErrorField(errorFields.get(k));
	                        errorTable.setErrorStatus('F');
	                        errorTable.setErrorRow("Row " + i);
	                        errorTableRepository.save(errorTable);
	                    }

	                    errorMessage.setCellValue(String.join(", ", errors));
	                    errorStatus.setCellValue("F");
	                } else {
	                    // Populate userEntity from row
	                    userEntity.setUserfullName(row.getCell(0).getStringCellValue());
	                    userEntity.setEmail(row.getCell(1).getStringCellValue());
	                    userEntity.setMobileNumber(getCellLongValue(row.getCell(2)));	                   
	                    userEntity.setAlternatemobileno(getCellLongValue(row.getCell(3)));
	                    userEntity.setAnnualincome(getCellLongValue(row.getCell(4)));
	                    userEntity.setPannumber(row.getCell(5).getStringCellValue());
	                    userEntity.setAadharnumber(String.valueOf(getCellLongValue(row.getCell(6))));
	                    userEntity.setGender(Gender.valueOf(row.getCell(7).getStringCellValue()));
	                    userEntity.setTitle(Title.valueOf(row.getCell(8).getStringCellValue()));
	                    userEntity.setUserAddress(row.getCell(9).getStringCellValue());
	                    userEntity.setUseraddress1(row.getCell(10).getStringCellValue());
	                    userEntity.setUseraddress2(row.getCell(11).getStringCellValue());
	                    userEntity.setUseraddress3(row.getCell(12).getStringCellValue());
	                    userEntity.setUserDob(getCellDateValue(row.getCell(13)));
	                    userEntity.setUsercity(row.getCell(14).getStringCellValue());
	                    userEntity.setUserstate(row.getCell(15).getStringCellValue());

	                    UserEntity savedUser = repository.save(userEntity);
	                    
	                    System.out.println("Processing Row: " + i + ", Cell 2 value: " + row.getCell(2));


	                    ErrorTable successLog = new ErrorTable();
	                    successLog.setErrorMessage("Saved user ID: " + savedUser.getUserId());
	                    successLog.setErrorField("");
	                    successLog.setErrorStatus('S');
	                    successLog.setErrorRow("Row " + i);
	                    errorTableRepository.save(successLog);

	                    errorMessage.setCellValue("Saved user ID: " + savedUser.getUserId());
	                    errorStatus.setCellValue("Success");
	                }

	                queuTable.setRowRead(i);
	            }

	            if (queuTable.getRowRead() >= totalrow) {
	                queuTable.setIsProcess("Y");
	                queuTable.setStatus("Y");
	            }

	            try (FileOutputStream outs = new FileOutputStream(filePath)) {
	                workbook.write(outs);
	            }

	            queuTable.setFilePath(filePath);
	            queuRepository.save(queuTable);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


	private Long getCellLongValue(Cell cell) {
	    if (cell == null) {
	        System.out.println("Cell is null");
	        return null;
	    }

	    try {
	        if (cell.getCellType() == CellType.NUMERIC) {
	            return (long) cell.getNumericCellValue();
	        } else if (cell.getCellType() == CellType.STRING) {
	            String val = cell.getStringCellValue().trim();
	            System.out.println("Parsing string cell: " + val);
	            if (!val.isEmpty() && val.matches("\\d+")) {
	                return Long.parseLong(val);
	            }
	        } else {
	            System.out.println("Unsupported cell type: " + cell.getCellType());
	        }
	    } catch (Exception e) {
	        System.out.println("Failed to read numeric value from cell: " + e.getMessage());
	    }

	    return null;
	}
	
	private Date getCellDateValue(Cell cell) {
	    if (cell == null) return null;

	    try {
	        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
	            return cell.getDateCellValue();
	        } else if (cell.getCellType() == CellType.STRING) {
	            String val = cell.getStringCellValue().trim();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // or adjust format as needed
	            return sdf.parse(val);
	        }
	    } catch (Exception e) {
	        System.out.println("Failed to parse date: " + e.getMessage());
	    }

	    return null;
	}


}
