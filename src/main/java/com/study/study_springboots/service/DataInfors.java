package com.study.study_springboots.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.study.study_springboots.beans.BoardBean;

@Service(value = "")//value는 이름을 설정 근데 거의 사용x 보통 carmel법으로 클래스이름으로 등록됨
public class DataInfors {
    public HashMap<String, String> getSearchFormData() {
        HashMap<String, String> searchForm = new HashMap<String, String>();
        searchForm.put("search_key", "Search Title");
        searchForm.put("name", "요주의랩!");
        searchForm.put("id", "ID0001");
        return searchForm;
    }

    public ArrayList<String> getTablesListWithString() {
        ArrayList<String> tablesListWithString = new ArrayList<String>();
        tablesListWithString.add("@mdo");
        tablesListWithString.add("@fat");
        tablesListWithString.add("@twitter");
        return tablesListWithString;
    }

    public HashMap<String, Object> getBundlesData() {
        DataInfors DataInfors = new DataInfors();
        HashMap<String, String> searchForm = DataInfors.getSearchFormData();
        ArrayList<String> tablesListWithString = DataInfors.getTablesListWithString();

        HashMap<String, Object> bundlesData = new HashMap<>();
        bundlesData.put("searchForm", searchForm);
        bundlesData.put("tablesListWithString", tablesListWithString);

        // bundlesData.put("dataWithMamberBean", DataInfors.getDataWithMamberBean());
        bundlesData.put("dataListWithBoardBean", DataInfors.getDataListWithBoardBean());

        return bundlesData;
    }

    public BoardBean getDataWithMamberBean(String title){
        ArrayList<BoardBean> memberList = getDataListWithBoardBean();
        BoardBean boardBean = new BoardBean();
        for(BoardBean member : memberList) {
            if(member.getTitle().equals(title)) {
                boardBean.setTitle(member.getTitle());
                boardBean.setContent(member.getContent());
                boardBean.setUserName(member.getUserName());
                boardBean.setDate(member.getDate());
                return boardBean;
            } 
        }
        boardBean.setTitle("에러) 없습니다");
        boardBean.setContent("Otto");
        boardBean.setUserName("@mdo");

        return boardBean;
    }

    public ArrayList<BoardBean> getDataListWithBoardBean() {
        ArrayList<BoardBean> membersList = new ArrayList<>();
        BoardBean boardBean = new BoardBean();
        boardBean.setTitle("시름하는 중국, 활짝 웃는 베트남…애플·레고·코카콜라 몰려온다");
        boardBean.setContent("베트남 통계청(GSO)은 지난달 29일 2022년 국내총생산(GDP) 성장률이 8.02%로 집계됐다고 밝혔다." +
        "한해 전인 2021년 2.58%보다 세배 넘게 성장했고, 2022년 목표치였던 6.0~6.5%를 훌쩍 넘긴 수치였다. "+
        "<로이터> 통신은 베트남 경제가 1997년 이후 최고 성장률을 기록했다며, 코로나19 대유행이 끝나 국민들의 소비 여력이 높아졌고, "+
        "지역 내 제조업 공장들이 생산을 재개한 점을 성장의 원인으로 꼽았다. 세계 경제엔 저성장의 그림자가 드리웠지만, "+
        "국제통화기금(IMF)은 지난해 4월 발간한 ‘세계 경제 전망’ 보고서를 통해 베트남이 2023년부터 2027년까지 연평균 6.96% 성장률을 기록할 것이라 예측했다.");
        boardBean.setUserName("김미향 기자");
        boardBean.setDate("2023-01-12");
        membersList.add(boardBean);

        boardBean = new BoardBean();
        boardBean.setTitle("성층권 머물며 지면 샅샅이 정찰… 위성으로 원거리 조종 가능");
        boardBean.setContent("지난해 말 북한의 드론(소형 무인기)이 수도권 상공을 침범했다. "+
        "백주 대낮에 경기 북부와 서울 한복판을 휘젓고 다녔다. 군은 드론을 포착했으나 격추시키지 못했다. "+
        "드론에 우리 하늘이 속수무책으로 뚫린 것이다."+
        "이번 사태는 드론 기술의 발전과 궤를 같이한다. "+
        "날개 길이 1m 이내의 아주 작은 드론이 더 빠르게, 더 멀리 그리고 더 높게 날 수 있는 능력을 갖게 되며 대응에 어려움을 겪게 된 것이다. "+
        "전문가들은 북한의 드론이 한국 전역의 하늘을 위협하는 상황이 펼쳐질 수도 있다고 전망한다.");
        boardBean.setUserName("고재원 동아사이언스기자");
        boardBean.setDate("2023-01-13");
        membersList.add(boardBean);

        boardBean = new BoardBean();
        boardBean.setTitle("“수학·과학 놀면서 배워요”…울산 방학 프로그램 ‘눈길’");
        boardBean.setContent("겨울방학을 맞아 울산교육청이 마련한 수학과 과학 체험 행사가 인기입니다."+
        "모두 무료로 진행되는데, 사전 예약 프로그램은 신청이 조기에 마감될 정도입니다."+
        "보도에 박영하 기자입니다.");
        boardBean.setUserName("박영하 기자");
        boardBean.setDate("2023-01-12");
        membersList.add(boardBean);
        return membersList;
    }

    public BoardBean addDataBean(HashMap<String,String> hashMap) {
        String title = hashMap.get("title");
        String content = hashMap.get("content");
        String userName = hashMap.get("userName");
        String date = hashMap.get("date");

        BoardBean bean = new BoardBean();
        bean.setTitle(title);
        bean.setContent(content);
        bean.setUserName(userName);
        bean.setDate(date);

        return bean;
    }
    
}