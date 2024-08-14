package kr.co.gd.service.impl;

import kr.co.gd.Entity.Omron;
import kr.co.gd.repository.OmronRepository;
import kr.co.gd.service.OmronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OmronServiceImpl implements OmronService {

    @Autowired
    private OmronRepository omronRepository;

    @Override
    public void bulkInsert() {
        List<Omron> insertList = new ArrayList<>();

        Omron omron = new Omron();
        omron.setCommand("DPICK");
        omron.setSeq(1);
        insertList.add(omron);

        Omron omron2 = new Omron();
        omron2.setCommand("pick_D1");
        omron2.setSeq(2);
        insertList.add(omron2);

        Omron omron3 = new Omron();
        omron3.setCommand("DSERV");
        omron3.setSeq(3);
        insertList.add(omron3);

        Omron omron4 = new Omron();
        omron4.setCommand("serv_D1");
        omron4.setSeq(4);
        insertList.add(omron4);

        Omron omron5 = new Omron();
        omron5.setCommand("DWAIT");
        omron5.setSeq(5);
        insertList.add(omron5);

        omronRepository.saveAll(insertList);
    }

    @Override
    public List<Omron> getCmdList() {
        return omronRepository.findAll();
    }

    @Override
    public Map<Integer, String> getCmdMap() {
        Map<Integer, String> resultMap = new HashMap<>();
        List<Omron> cmdList = omronRepository.findAll();

        for (Omron omron : cmdList) {
            resultMap.put(omron.getSeq(), omron.getCommand());
        }

        return resultMap;
    }
}
