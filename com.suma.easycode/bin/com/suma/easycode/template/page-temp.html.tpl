<div id="page-${pageName}-wrapper" class="page-wrapper">
    <bvc2-header
            :links="header.links"
            :user="header.user"></bvc2-header>
    <div class="page-body" style="padding:0;">
        <el-container style="width:100%; height:100%">
            <el-aside width="auto;">
                <bvc2-system-nav-side :active="side.active"></bvc2-system-nav-side>
            </el-aside>
            <el-main style="padding:15px;">
                <div class="bvc2-card">
                    <div class="bvc2-card-head no-border"></div>
                    <div class="bvc2-card-body">
                        <!-- 表 -->
                        <bvc2-system-table-base
                                :buttoncreate="${pageName}.buttonCreate"
                                :buttonremove="${pageName}.buttonCreate"
                                :columns="${pageName}.columns"
                                :options="${pageName}.options"
                                :load="${pageName}.load"
                                :save="${pageName}.save"
                                :update="${pageName}.update"
                                :remove="${pageName}.remove"
                                :removebatch="${pageName}.removebatch"
                                :pk="${pageName}.pk"
                                @edit-gears="editGears"></bvc2-system-table-base>
                    </div>
                </div>
            </el-main>
        </el-container>
    </div>
</div>