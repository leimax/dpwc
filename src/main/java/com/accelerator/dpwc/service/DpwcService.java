package com.accelerator.dpwc.service;

import com.accelerator.dpwc.domain.Clock;

import java.util.List;
import java.util.Map;

public interface DpwcService {

    void addUser(String username, String password);

    void delUser(String username);

    List<Clock> getClocks(String dateStr);

    void addClock(String dateStr, Integer type);

    void addClocks(Map<String, Integer> params);

    void schedule(boolean isClockIn);

}
