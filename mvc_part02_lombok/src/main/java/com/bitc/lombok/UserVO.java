package com.bitc.lombok;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

// @Data
/**
 * exclude - 제외 시킬 필드명을 지정하면 해당 필드는 제외 
 * of	   - 생성할 필드명 작성 , 해당 필드로만 생성
 * callSuper - 상위 클래스 호출 여부
 */
@Getter
@Setter
@ToString(of = {"uno","uname","friendList"})
@EqualsAndHashCode(exclude = {"uno","uname","regdate","friendList"})
//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserVO {

	private int uno;

	@NonNull
	private String uid;
	@Getter
	private final String upw;
	@Setter
	private String uname;
	private Date regdate;
	
	@Singular("list")
	private List<String> friendList;
	
}
