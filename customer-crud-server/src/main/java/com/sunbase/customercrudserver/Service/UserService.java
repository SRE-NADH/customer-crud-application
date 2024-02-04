package com.sunbase.customercrudserver.Service;

import com.sunbase.customercrudserver.Dto.ReqDto.UserReqDto;
import com.sunbase.customercrudserver.Dto.ResDto.UserResDto;

public interface UserService {
    UserResDto RegisterUser(UserReqDto userReqDto);
}
