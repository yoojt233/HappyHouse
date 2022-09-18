package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.BookmarkDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.ReserveDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong, int page) throws Exception {
		return houseMapMapper.getAptInDong(dong, page);
	}

	@Override
	public List<HouseInfoDto> getAptBookmark(String userid) throws Exception {
		return houseMapMapper.getAptBookmark(userid);
	}

	@Override
	public boolean bookmarkAdd(BookmarkDto bookmarkDto) throws Exception {
		return houseMapMapper.bookmarkAdd(bookmarkDto);
	}

	@Override
	public int bookmarked(BookmarkDto bookmarkDto) throws Exception {
		return houseMapMapper.bookmarked(bookmarkDto);
	}

	@Override
	public boolean bookmarkRemove(BookmarkDto bookmarkDto) throws Exception {
		return houseMapMapper.bookmarkRemove(bookmarkDto);
	}
	
	@Override
	public int aptCnt(String dong) throws Exception{
		return houseMapMapper.aptCnt(dong);
	};

	@Override
	public boolean reserveAdd(ReserveDto reserveDto) throws Exception {
		return houseMapMapper.reserveAdd(reserveDto);
	}

	@Override
	public String reserved(ReserveDto reserveDto) throws Exception {
		return houseMapMapper.reserved(reserveDto);
	}

	@Override
	public boolean reserveRemove(ReserveDto reserveDto) throws Exception {
		return houseMapMapper.reserveRemove(reserveDto);
	}

	@Override
	public int bookmarkCount(String userid) throws Exception {
		return houseMapMapper.bookmarkCount(userid);
	}

}
