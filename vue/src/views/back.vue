<template>
    <div>
      <el-row style="margin-left: 20px;margin-top: 20px;">
        <el-button type="primary" @click="report1">下载问卷一的Excel</el-button>
        <el-button type="primary" @click="report2">下载问卷二的Excel</el-button>
        <el-button type="primary" @click="report3">下载问卷三的Excel</el-button>
        <el-button type="primary" @click="reportAll">下载所有问卷的Excel</el-button>
        <br>
        <el-input v-model="answerUser" placeholder="请输入姓名" style="width: 200px;margin-top: 20px;margin-right: 50px;"></el-input>
        <el-button type="primary" @click="click()">查询</el-button>
      </el-row>
        <el-container style="padding: 0px;">
            <el-container>                
                <el-main>
                    <el-table :data="tableData" border stripe fit>
                        <el-table-column prop="answerUser" label="姓名" width="80" sortable>
                        </el-table-column>
                        <el-table-column prop="answerBeginTime" label="开始作答时间" width="180" sortable>
                        </el-table-column>
                        <el-table-column prop="answerTime" label="结束作答时间" width="180" sortable>
                        </el-table-column>
                        <el-table-column prop="usedTime" :formatter="subtractColumns" label="使用时长(s)">
                        </el-table-column>
                        <el-table-column prop="surveyName" label="问卷" width="100"  sortable>
                        </el-table-column>
                        <el-table-column prop="hostAddress" label="主机ip" width="150">
                        </el-table-column>
                        <el-table-column prop="hostName" label="主机名称" width="150">
                        </el-table-column>
                        <el-table-column prop="answer1" label="答案一" width="100">
                        </el-table-column>
                        <el-table-column prop="answer2" label="答案二" width="100">
                        </el-table-column>
                        <el-table-column prop="answer3" label="答案三" width="100">
                        </el-table-column>
                        <el-table-column prop="answer4" label="答案四" width="100">
                        </el-table-column>
                        <el-table-column prop="answer5" label="答案五" width="100">
                        </el-table-column>
                        <el-table-column prop="answer6" label="答案六" width="100">
                        </el-table-column>
                        <el-table-column prop="answer7" label="答案七" width="100">
                        </el-table-column>
                        <el-table-column prop="answer8" label="答案八" width="100">
                        </el-table-column>
                        <el-table-column prop="answer9" label="答案九" width="100">
                        </el-table-column>
                        <el-table-column prop="answer10" label="答案十" width="100">
                        </el-table-column>
                        <el-table-column prop="answer11" label="答案十一" width="100">
                        </el-table-column>
                        <el-table-column prop="answer12" label="答案十二" width="100">
                        </el-table-column>
                        <el-table-column prop="answer13" label="答案十三" width="100">
                        </el-table-column>
                        <el-table-column prop="answer14" label="答案十四" width="100">
                        </el-table-column>
                        <el-table-column prop="answer15" label="答案十五" width="100">
                        </el-table-column>
                        <el-table-column prop="answer16" label="答案十六" width="100">
                        </el-table-column>
                        <el-table-column prop="answer17" label="答案十七" width="100">
                        </el-table-column>
                        <el-table-column prop="answer18" label="答案十八" width="100">
                        </el-table-column>
                        <el-table-column prop="answer19" label="答案十九" width="100">
                        </el-table-column>
                        <el-table-column prop="answer20" label="答案二十" width="100">
                        </el-table-column>
                        <el-table-column prop="answer21" label="答案二十一" width="100">
                        </el-table-column>
                        <el-table-column prop="answer22" label="答案二十二" width="100">
                        </el-table-column>
                        <el-table-column prop="answer23" label="答案二十三" width="100">
                        </el-table-column>
                        <el-table-column prop="answer24" label="答案二十四" width="100">
                        </el-table-column>
                        <el-table-column prop="answer25" label="答案二十五" width="100">
                        </el-table-column>
                        <el-table-column prop="answer26" label="答案二十六" width="100">
                        </el-table-column>
                        <el-table-column prop="answer27" label="答案二十七" width="100">
                        </el-table-column>
                        <el-table-column prop="answer28" label="答案二十八" width="100">
                        </el-table-column>
                        <el-table-column prop="answer29" label="答案二十九" width="100">
                        </el-table-column>
                        <el-table-column prop="answer30" label="答案三十" width="100">
                        </el-table-column>
                        <el-table-column prop="answer31" label="答案三十一" width="100">
                        </el-table-column>
                        <el-table-column prop="answer32" label="答案三十二" width="100">
                        </el-table-column>
                        <el-table-column prop="answer33" label="答案三十三" width="100">
                        </el-table-column>
                        <el-table-column prop="answer34" label="答案三十四" width="100">
                        </el-table-column>
                        <el-table-column prop="answer35" label="答案三十五" width="100">
                        </el-table-column>
                        <el-table-column prop="answer36" label="答案三十六" width="100">
                        </el-table-column>
                        <el-table-column prop="answer37" label="答案三十七" width="100">
                        </el-table-column>
                        <el-table-column prop="answer38" label="答案三十八" width="100">
                        </el-table-column>
                        <el-table-column prop="answer39" label="答案三十九" width="100">
                        </el-table-column>
                        <el-table-column prop="answer40" label="答案四十" width="100">
                        </el-table-column>
                        <el-table-column prop="answer41" label="答案四十一" width="100">
                        </el-table-column>
                        <el-table-column prop="answer42" label="答案四十二" width="100">
                        </el-table-column>
                        <el-table-column prop="answer43" label="答案四十三" width="100">
                        </el-table-column>
                        <el-table-column prop="answer44" label="答案四十四" width="100">
                        </el-table-column>
                        <el-table-column prop="answer45" label="答案四十五" width="100">
                        </el-table-column>
                        <el-table-column prop="answer46" label="答案四十六" width="100">
                        </el-table-column>
                        <el-table-column prop="answer47" label="答案四十七" width="100">
                        </el-table-column>
                        <el-table-column prop="answer48" label="答案四十八" width="100">
                        </el-table-column>
                        <el-table-column prop="answer49" label="答案四十九" width="100">
                        </el-table-column>
                        <el-table-column prop="answer50" label="答案五十" width="100">
                        </el-table-column>
                        <el-table-column prop="answer51" label="答案五十一" width="100">
                        </el-table-column>
                        <el-table-column prop="answer52" label="答案五十二" width="100">
                        </el-table-column>
                        <el-table-column prop="answer53" label="答案五十三" width="100">
                        </el-table-column>
                        <el-table-column prop="answer54" label="答案五十四" width="100">
                        </el-table-column>
                        <el-table-column prop="answer55" label="答案五十五" width="100">
                        </el-table-column>
                        <el-table-column prop="answer56" label="答案五十六" width="100">
                        </el-table-column>
                        <el-table-column prop="answer57" label="答案五十七" width="100">
                        </el-table-column>
                        <el-table-column prop="answer58" label="答案五十八" width="100">
                        </el-table-column>
                        <el-table-column prop="answer59" label="答案五十九" width="100">
                        </el-table-column>
                        <el-table-column prop="answer60" label="答案六十" width="100">
                        </el-table-column>
                    </el-table>
                    <div class="tabListPage">
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" 
                              :page-sizes="pageSizes" :page-size="PageSize" layout="total, sizes, prev, pager, next, jumper" :total="totalCount">
                        </el-pagination>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
  export default {
    data() {
      return {
          // 总数据
          tableData:[],
          // 默认显示第几页
          currentPage:1,
          // 总条数，根据接口获取数据长度(注意：这里不能为空)
          totalCount:1,
          // 个数选择器（可修改）
          pageSizes:[2,5,10,20,50,100],
          // 默认每页显示的条数（可修改）
          PageSize:20,
          // 用户名
          answerUser: '',
      }
    },
    created() {
      this.load();
    },
    methods: {
      subtractColumns(row){
        return (new Date(row.answerTime).getTime() - new Date(row.answerBeginTime).getTime())/1000;
      },
      load(){
        this.request.get("/answer/findByPage?pageNum="+this.currentPage+"&pageSize="+this.PageSize+"&answerUser="+this.answerUser).then(res => {
          // 返回查询的数据
          this.tableData = res.data;
          // 将数据的长度赋值给totalCount
          this.totalCount=res.total;
          console.log(this.totalCount)
        })
      },
      report1() {
        window.open("http://127.0.0.1:9090/answer/report1")
      },
      report2() {
        window.open("http://127.0.0.1:9090/answer/report2")
      },
      report3() {
        window.open("http://127.0.0.1:9090/answer/report3")
      },
      reportAll() {
        window.open("http://127.0.0.1:9090/answer/reportAll")
      },
      click(){
          // 改变默认的页数
          this.currentPage=1
          this.load();
      },
      // 分页
      // 每页显示的条数
      handleSizeChange(val) {
          // 改变每页显示的条数 
          this.PageSize=val
          // 注意：在改变每页显示的条数时，要将页码显示到第一页
          this.currentPage=1
          this.load();

      },
        // 显示第几页
      handleCurrentChange(val) {
          // 改变默认的页数
          this.currentPage=val
          this.load();
      },
    }
  };
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>
  