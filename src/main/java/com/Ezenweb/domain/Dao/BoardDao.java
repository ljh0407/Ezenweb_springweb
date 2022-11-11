package com.Ezenweb.domain.Dao;

import com.Ezenweb.domain.Dto.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public BoardDao() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root",
                    "1234");
        } catch (Exception e) {
            System.out.println("연동실패" + e);
        }
    }

    // 1. 게시물 등록
    public boolean setboard(BoardDto boardDto) {
        String sql = "insert into board( btitle , bcontent ) values( ? , ? )";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);}return false;
    }

    // 2. 게시물 목록
    public ArrayList<BoardDto> getboards() {
        ArrayList<BoardDto> boards = new ArrayList<>();
        String sql = "select * from board";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                boards.add( // 리스트 추가
                        BoardDto // dto 생성
                                .builder()  // 빌더 패턴 시작
                                .btitle(rs.getString(1))    
                                .bcontent(rs.getString(2))
                                .build()    // 빌더 패턴 끝
                );
            }
        } catch (Exception e) {System.out.println(e);} return  boards;

    }
}
