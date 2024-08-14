package kr.co.gd.service;

import kr.co.gd.Entity.Omron;

import java.util.List;
import java.util.Map;

public interface OmronService {
    void bulkInsert();
    List<Omron> getCmdList();
    Map<Integer, String> getCmdMap();
}
