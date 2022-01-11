package records.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import account.LoginInfo;
import domain.ThingsTb;
import records.RecordsService;
import records.dto.ThingsReqDto;

@Controller
public class RecordsController {

    private RecordsService recordsService;

    public void setRecordsController(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    //===== ===== ===== =====//
    // 홈페이지
    //===== ===== ===== =====//
    @GetMapping("/records")
    public String recordsMain(ThingsReqDto thingsReqDto, HttpSession session, Model model) {
        // 이미 등록된 세션으로 LoginInfo 객체 생성 -  user key Id 가져옴
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        Long loginId = loginInfo.getId();
        // 오늘 기록된 ThingsTb 행들, DB에서 가져옴
        List<ThingsTb> thingsTbs = recordsService.selectThingsToday(loginId);
        model.addAttribute("thingsTbs", thingsTbs);
        // 
        return "records/recordsMain";
    }

    /**
     * things 테이블에 기록
     * @param thingsReqDto  // dto
     * @param session       // 이미 등록된 세션 가져오기 위한
     * @return
     */
    @PostMapping("/records")
    public String recordsRecording(ThingsReqDto thingsReqDto, HttpSession session) {
        // 이미 등록된 세션으로 LoginInfo 객체 생성 -  user key Id 가져옴
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        Long loginId = loginInfo.getId();

        // things 테이블에 기록
        Long key = recordsService.insertThings(thingsReqDto, loginId);

        // tag 테이블과 things_tag 테이블에 기록
        recordsService.insertTags(thingsReqDto, key);
        
        //
        return "records/recordsMain";
    }
    @PostMapping("/recordsShow")
    public String recordsShowing(ThingsReqDto thingsReqDto, HttpServletRequest request, HttpSession session, Model model) {
        // 이미 등록된 세션으로 LoginInfo 객체 생성 -  user key Id 가져옴
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        Long loginId = loginInfo.getId();

        // 요청된 날(?????) 가져오기
        String stringDate = request.getParameter("someday");
        // 요청된 날(?????)에 기록된 ThingsTb 행들, DB에서 가져옴
        List<ThingsTb> thingsTbs = recordsService.selectThingsSomeday(stringDate, loginId);
        model.addAttribute("thingsTbs", thingsTbs);
        // 
        return "records/recordsMain";
    }
    
}