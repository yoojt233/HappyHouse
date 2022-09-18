package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.BookmarkDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.ReserveDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong, int page) throws Exception;
	List<HouseInfoDto> getAptBookmark(String userid) throws Exception;
	public boolean bookmarkAdd(BookmarkDto bookmarkDto) throws Exception;
	public boolean bookmarkRemove(BookmarkDto bookmarkDto) throws Exception;
	public int bookmarked(BookmarkDto bookmarkDto) throws Exception;
	public int bookmarkCount(String userid) throws Exception;
	public int aptCnt(String dong) throws Exception;
	public boolean reserveAdd(ReserveDto reserveDto) throws Exception;
	public String reserved(ReserveDto reserveDto) throws Exception;
	public boolean reserveRemove(ReserveDto reserveDto) throws Exception;
}
