<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <li id="all_file_li">
                <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a href="AllFileInGalleryIndexAction.action">
                    <i class="icon-folder-open"></i>
                    <span>全部文件</span>
                </a>
            </li>            
            <li id="document_file_li">
                <a href="TypeFileInGalleryIndexAction.action?category=1">
                    <i class="icon-folder-close"></i>
                    <span>文档</span>
                </a>
            </li>
            <li id="picture_file_li">
                <a href="TypeFileInGalleryIndexAction.action?category=2">
                    <i class="icon-picture"></i>
                    <span>图片</span>
                </a>
            </li>
            <li id="video_file_li">
                <a href="TypeFileInGalleryIndexAction.action?category=3">
                    <i class="icon-film"></i>
                    <span>视频</span>
                </a>
            </li>
            <li id="music_file_li">
                <a href="TypeFileInGalleryIndexAction.action?category=4">
                    <i class="icon-music"></i>
                    <span>音频</span>
                </a>
            </li>
            <li id="my_share_li">
                <a href="ShareIndexAction.action">
                    <i class="icon-external-link"></i>
                    <span>我的分享</span>
                </a>
            </li>
            <li id="my_group_li">
                <a href="/HTP/pages/my_group.jsp">
                    <i class="icon-group"></i>
                    <span>我的群组</span>
                </a>
            </li>
        </ul>
    </div>
    <!-- end sidebar -->