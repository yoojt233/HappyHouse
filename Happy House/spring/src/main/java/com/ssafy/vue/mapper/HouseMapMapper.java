package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.BookmarkDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.ReserveDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong, int page) throws SQLException;
	List<HouseInfoDto> getAptBookmark(String userid) throws SQLException;
	public boolean bookmarkAdd(BookmarkDto bookmarkDto) throws SQLException;
	public boolean bookmarkRemove(BookmarkDto bookmarkDto) throws SQLException;
	public int bookmarked(BookmarkDto bookmarkDto) throws SQLException;
	public int bookmarkCount(String userid) throws SQLException;
	public int aptCnt(String dong);
	public boolean reserveAdd(ReserveDto reserveDto) throws SQLException;
	public String reserved(ReserveDto reserveDto) throws SQLException;
	public boolean reserveRemove(ReserveDto reserveDto) throws SQLException;
}
