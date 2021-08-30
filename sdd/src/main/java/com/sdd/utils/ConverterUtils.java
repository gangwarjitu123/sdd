package com.sdd.utils;

import com.sdd.entities.Role;
import com.sdd.entities.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterUtils {

    public static Map<Integer, Role> getRoleVsMap(List<Role> roles){
        Map<Integer, com.sdd.entities.Role> roleMap = new HashMap<>();
        for (Role role : roles){
            roleMap.put(role.getRoleId(),role);
        }
        return roleMap;
    }

    public static Map<Integer, State> getStateMap(List<State> states){
        Map<Integer,State> stateMap = new HashMap<>();
        for (State state: states){
            stateMap.put(state.getId(),state);
        }
        return stateMap;
    }
}
