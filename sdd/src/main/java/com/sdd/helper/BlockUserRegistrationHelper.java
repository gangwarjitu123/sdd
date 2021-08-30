package com.sdd.helper;

import com.sdd.entities.Block;
import com.sdd.entities.User;
import com.sdd.entities.repository.BlockRepository;
import com.sdd.exception.SDDException;
import com.sdd.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("BLOCK")
public class BlockUserRegistrationHelper extends AbstractUserRegistrationHelper{
   @Autowired
   private  BlockRepository blockRepository;

    @Override
    protected void fillUserDataBasedOnRole(UserCreateRequest userCreateRequest, User partialFieldUser) {
        Block block = blockRepository.findAllByDistrictCodeAndStateIdAndHealthBlockCode(partialFieldUser.getDistrictCode(), partialFieldUser.getStateId(), userCreateRequest.getBlockCode());
        partialFieldUser.setBlockCode(block.getHealthBlockCode());
        return;
    }

    private void validateBlock(UserCreateRequest userCreateRequest){
        if(userCreateRequest.getBlockCode()==null){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"block is blank");
        }
    }
}
