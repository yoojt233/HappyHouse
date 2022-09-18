package com.ssafy.vue.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.BookmarkDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.ReserveDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.service.HouseMapService;

@RestController
@RequestMapping("/map")
//@CrossOrigin("*")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseMapService haHouseMapService;

	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
//		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
//		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping("/apt/{dong}/{page}")
	public ResponseEntity<List<HouseInfoDto>> apt(@PathVariable String dong, @PathVariable int page) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong, (page - 1) * 8),
				HttpStatus.OK);
	}
	
	@GetMapping("/cnt/{dong}")
	public ResponseEntity<Integer> aptCnt(@PathVariable String dong) throws Exception {
		int temp = haHouseMapService.aptCnt(dong);
		return new ResponseEntity<Integer>(temp, HttpStatus.OK);
	}

	// 주소가 어디서 꼬인거지????
	@GetMapping("map/bookmark/{userid}")
	public ResponseEntity<List<HouseInfoDto>> aptBookmark(@PathVariable String userid) throws Exception {
		System.out.println("aptBookmark 출력");
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptBookmark(userid), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> bookmarkAdd(@RequestBody BookmarkDto bookmarkDto) throws Exception {
		logger.debug("bookmarkAdd - 호출");

		if (haHouseMapService.bookmarkAdd(bookmarkDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/reserve")
	public ResponseEntity<String> reserveAdd(@RequestBody ReserveDto reserveDto) throws Exception {
		logger.debug("reserveAdd - 호출");
		
		if (haHouseMapService.reserveAdd(reserveDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}	
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/remove/{aptCode}/{userid}")
	public ResponseEntity<String> bookmarkRemove(@PathVariable int aptCode, @PathVariable String userid) throws Exception {
		logger.debug("bookmarkRemove - 호출");

		if (haHouseMapService.bookmarkRemove(new BookmarkDto(aptCode, userid, "",""))) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/find/{aptCode}/{userid}")
	public ResponseEntity<Integer> bookmarked(@PathVariable int aptCode, @PathVariable String userid) throws Exception {
		logger.debug("bookmarked - 호출");
		return new ResponseEntity<Integer>(haHouseMapService.bookmarked(new BookmarkDto(aptCode, userid, "","")),
				HttpStatus.OK);
	}
	
	@GetMapping("/rfind/{aptCode}/{userid}")
	public ResponseEntity<String> reserved(@PathVariable int aptCode, @PathVariable String userid) throws Exception {
		logger.debug("reserved - 호출");
		return new ResponseEntity<String>(haHouseMapService.reserved(new ReserveDto(aptCode,userid,"")), HttpStatus.OK);
	}
	
	@DeleteMapping("/rmreserve/{aptCode}/{userid}")
	public ResponseEntity<String> reserveRemove(@PathVariable int aptCode, @PathVariable String userid) throws Exception {
		logger.debug("bookmarkRemove - 호출");
		
		if (haHouseMapService.reserveRemove(new ReserveDto(aptCode,userid,""))) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}	
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/bmcount/{userid}")
	public ResponseEntity<Integer> bookmarkCount(@PathVariable String userid) throws Exception {
		logger.debug("bookmarkCount - 호출");
		System.out.printf("%s %d %n",userid,haHouseMapService.bookmarkCount(userid));
		return new ResponseEntity<Integer>(haHouseMapService.bookmarkCount(userid),
				HttpStatus.OK);
	}

}
