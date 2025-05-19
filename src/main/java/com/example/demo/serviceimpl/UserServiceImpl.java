package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.enums.Gender;
import com.example.demo.enums.Title;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demoPaagination.SearchFilter;
import com.example.demoPaagination.UserPagination;

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

//	@Autowired
//	private UserEntity entity;

	@Autowired
	private EntityManager entityManager;

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
//		String mobileNo = userdto.getMobileNumber();
//		if (mobileNo == null ) {
//			throw new IllegalArgumentException("Please enter valid mobile number");
//		}
//		entity.setMobileNumber(mobileNo);

		// Annual Income Validation and Mapping
		Long annualIncome = userdto.getAnnualincome();

		entity.setAnnualincome(annualIncome);

		// User Address Mapping
		String userAddress = userdto.getUseraddress();
//		if (userAddress == null) {
//			throw new IllegalArgumentException("Please enter a valid address");
//		}
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
//		if (city == null || !city.isEmpty()) {
//			throw new IllegalArgumentException("Enter City");
//		}
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

		String mobileno = userdto.getMobileNumber();
		if (mobileno != null && !mobileno.isEmpty()) {
			entity.setMobileNumber(mobileno);
		}

		// Alternate Mobile Number Mapping
		String alternatemobileno = userdto.getAlternatemobileno();
		if (alternatemobileno != null && !alternatemobileno.isEmpty()) {
			entity.setAlternatemobileno(alternatemobileno);
		}

//GENDER Validation and Mapping 
		Gender gender = userdto.getGender();
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
//	    if (state == null || state.isEmpty()) {
//	        throw new IllegalArgumentException("Enter State");
//	    }
		entity.setUserstate(state);
		entity.setUserstatus('Y');

		// Save to Database
		UserEntity save = repository.save(entity);
		Character userStatus = save.getUserstatus();
		System.out.println(" userStatus   " + userStatus);
		return "User Added Successfully";
	}

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
			String mobileNumber = userdto.getMobileNumber();
			if (mobileNumber != null) {
				if (mobileNumber.matches("^[6-9]\\d{9}$")) {
					existingEntity.setMobileNumber(mobileNumber);
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

//	         Address lines
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

//	        // Aadhar
//	        String aadharNumber = userdto.getAadharNumber();
//	        if (aadharNumber != null) {
//	            if (aadharNumber.matches("^[2-9]{1}[0-9]{11}$")) {
//	                existingEntity.setAadharnumber(aadharNumber);
//	            } else {
//	                throw new IllegalArgumentException("Invalid Aadhar number");
//	            }
//	        }

			// Alternate Mobile
			String altMobile = userdto.getAlternatemobileno();
			if (altMobile != null) {
				if (altMobile.matches("^[6-9]\\d{9}$")) {
					existingEntity.setAlternatemobileno(altMobile);
				} else {
					throw new IllegalArgumentException("Invalid alternate mobile number");
				}
			}

			// GENDER Validation and Mapping
			Gender gender = userdto.getGender();
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
	public String deleteUserById(Long userId) {
		Optional<UserEntity> findbyuserid = repository.findById(userId);

		if (findbyuserid.isPresent()) {
			// datatype variable = code
			UserEntity userEntity = findbyuserid.get();

			userEntity.setUserstatus('N');
			repository.save(userEntity);
		}

		return "Deleted Successfully";
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
//	        dto.setUseraddress2(details.getUseraddress2());
//	        dto.setUseraddress3(details.getUseraddress3());
			dto.setAlternatemobileno(details.getAlternatemobileno());
			dto.setUserCity(details.getUsercity());
			dto.setEmail(details.getEmail());
			dto.setMobileNumber(details.getMobileNumber());
			dto.setUserState(details.getUserstate());
			dto.setGender(details.getGender());
			dto.setUserDob(details.getUserDob());
			// dto.setAadharNumber(details.getAadharnumber());
			dto.setPannumber(details.getPannumber());
//	        dto.setUserstatus(details.getUserstatus());
			dto.setAnnualincome(details.getAnnualincome());
			dto.setTitle(details.getTitle());
		}

		return dto;

	}
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
	    if(query.getResultList().stream().map(this::convertToMap).collect(Collectors.toList()).isEmpty()) {
	    	 throw new IllegalArgumentException("User not found");
	    }
	    
	    return query.getResultList().stream().map(this::convertToMap).collect(Collectors.toList());
	}

	// Map all fields
	private Map<String, Object> convertToMap(UserEntity userEntity) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("userId", userEntity.getUserId());
	    map.put("userdob", userEntity.getUserdob());
	    map.put("useraddress", userEntity.getUseraddress());
	    map.put("useraddress1", userEntity.getUseraddress1());
	    map.put("useraddress2", userEntity.getUseraddress2());
	    map.put("useraddress3", userEntity.getUseraddress3());
	    map.put("usercity", userEntity.getUsercity());
	    map.put("userstate", userEntity.getUserstate());
	    map.put("userfullName", userEntity.getUserfullName());
	    map.put("email", userEntity.getEmail());
	    map.put("mobileNumber", userEntity.getMobileNumber());
	    map.put("alternatemobileno", userEntity.getAlternatemobileno());
	    map.put("annualincome", userEntity.getAnnualincome());
	    map.put("userstatus", userEntity.getUserstatus());
	    map.put("pannumber", userEntity.getPannumber());
	    map.put("aadharnumber", userEntity.getAadharnumber());
	    map.put("gender", userEntity.getGender());
	    map.put("title", userEntity.getTitle());
	    return map;
	}


}