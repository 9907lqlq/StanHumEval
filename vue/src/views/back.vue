<template>
    <div>
      <el-row style="margin-left: 20px;margin-top: 20px;">
        <el-button type="primary" @click="report1">Download the Excel version of Questionnaire One</el-button>
        <el-button type="primary" @click="report2">Download the Excel version of Questionnaire Two</el-button>
        <el-button type="primary" @click="report3">Download the Excel file for Questionnaire Three</el-button>
        <el-button type="primary" @click="reportAll">Download all the questionnaires in Excel format</el-button>
        <br>
        <el-input v-model="answerUser" placeholder="Please enter your name" style="width: 200px;margin-top: 20px;margin-right: 50px;"></el-input>
        <el-button type="primary" @click="click()">query</el-button>
      </el-row>
        <el-container style="padding: 0px;">
            <el-container>                
                <el-main>
                    <el-table :data="tableData" border stripe fit>
                        <el-table-column prop="answerUser" label="answerUser" width="80" sortable>
                        </el-table-column>
                        <el-table-column prop="answerBeginTime" label="answerBeginTime" width="180" sortable>
                        </el-table-column>
                        <el-table-column prop="answerTime" label="answerTime" width="180" sortable>
                        </el-table-column>
                        <el-table-column prop="usedTime" :formatter="subtractColumns" label="usedTime(s)">
                        </el-table-column>
                        <el-table-column prop="surveyName" label="surveyName" width="100"  sortable>
                        </el-table-column>
                        <el-table-column prop="hostAddress" label="hostAddress" width="150">
                        </el-table-column>
                        <el-table-column prop="hostName" label="hostName" width="150">
                        </el-table-column>
                        <el-table-column prop="answer1" label="answer1" width="100">
                        </el-table-column>
                        <el-table-column prop="answer2" label="answer2" width="100">
                        </el-table-column>
                        <el-table-column prop="answer3" label="answer3" width="100">
                        </el-table-column>
                        <el-table-column prop="answer4" label="answer4" width="100">
                        </el-table-column>
                        <el-table-column prop="answer5" label="answer5" width="100">
                        </el-table-column>
                        <el-table-column prop="answer6" label="answer6" width="100">
                        </el-table-column>
                        <el-table-column prop="answer7" label="answer7" width="100">
                        </el-table-column>
                        <el-table-column prop="answer8" label="answer8" width="100">
                        </el-table-column>
                        <el-table-column prop="answer9" label="answer9" width="100">
                        </el-table-column>
                        <el-table-column prop="answer10" label="answer10" width="100">
                        </el-table-column>
                        <el-table-column prop="answer11" label="answer11" width="100">
                        </el-table-column>
                        <el-table-column prop="answer12" label="answer12" width="100">
                        </el-table-column>
                        <el-table-column prop="answer13" label="answer13" width="100">
                        </el-table-column>
                        <el-table-column prop="answer14" label="answer14" width="100">
                        </el-table-column>
                        <el-table-column prop="answer15" label="answer15" width="100">
                        </el-table-column>
                        <el-table-column prop="answer16" label="answer16" width="100">
                        </el-table-column>
                        <el-table-column prop="answer17" label="answer17" width="100">
                        </el-table-column>
                        <el-table-column prop="answer18" label="answer18" width="100">
                        </el-table-column>
                        <el-table-column prop="answer19" label="answer19" width="100">
                        </el-table-column>
                        <el-table-column prop="answer20" label="answer20" width="100">
                        </el-table-column>
                        <el-table-column prop="answer21" label="answer21" width="100">
                        </el-table-column>
                        <el-table-column prop="answer22" label="answer22" width="100">
                        </el-table-column>
                        <el-table-column prop="answer23" label="answer23" width="100">
                        </el-table-column>
                        <el-table-column prop="answer24" label="answer24" width="100">
                        </el-table-column>
                        <el-table-column prop="answer25" label="answer25" width="100">
                        </el-table-column>
                        <el-table-column prop="answer26" label="answer26" width="100">
                        </el-table-column>
                        <el-table-column prop="answer27" label="answer27" width="100">
                        </el-table-column>
                        <el-table-column prop="answer28" label="answer28" width="100">
                        </el-table-column>
                        <el-table-column prop="answer29" label="answer29" width="100">
                        </el-table-column>
                        <el-table-column prop="answer30" label="answer30" width="100">
                        </el-table-column>
                        <el-table-column prop="answer31" label="answer31" width="100">
                        </el-table-column>
                        <el-table-column prop="answer32" label="answer32" width="100">
                        </el-table-column>
                        <el-table-column prop="answer33" label="answer33" width="100">
                        </el-table-column>
                        <el-table-column prop="answer34" label="answer34" width="100">
                        </el-table-column>
                        <el-table-column prop="answer35" label="answer35" width="100">
                        </el-table-column>
                        <el-table-column prop="answer36" label="answer36" width="100">
                        </el-table-column>
                        <el-table-column prop="answer37" label="answer37" width="100">
                        </el-table-column>
                        <el-table-column prop="answer38" label="answer38" width="100">
                        </el-table-column>
                        <el-table-column prop="answer39" label="answer39" width="100">
                        </el-table-column>
                        <el-table-column prop="answer40" label="answer40" width="100">
                        </el-table-column>
                        <el-table-column prop="answer41" label="answer41" width="100">
                        </el-table-column>
                        <el-table-column prop="answer42" label="answer42" width="100">
                        </el-table-column>
                        <el-table-column prop="answer43" label="answer43" width="100">
                        </el-table-column>
                        <el-table-column prop="answer44" label="answer44" width="100">
                        </el-table-column>
                        <el-table-column prop="answer45" label="answer45" width="100">
                        </el-table-column>
                        <el-table-column prop="answer46" label="answer46" width="100">
                        </el-table-column>
                        <el-table-column prop="answer47" label="answer47" width="100">
                        </el-table-column>
                        <el-table-column prop="answer48" label="answer48" width="100">
                        </el-table-column>
                        <el-table-column prop="answer49" label="answer49" width="100">
                        </el-table-column>
                        <el-table-column prop="answer50" label="answer50" width="100">
                        </el-table-column>
                        <el-table-column prop="answer51" label="answer51" width="100">
                        </el-table-column>
                        <el-table-column prop="answer52" label="answer52" width="100">
                        </el-table-column>
                        <el-table-column prop="answer53" label="answer53" width="100">
                        </el-table-column>
                        <el-table-column prop="answer54" label="answer54" width="100">
                        </el-table-column>
                        <el-table-column prop="answer55" label="answer55" width="100">
                        </el-table-column>
                        <el-table-column prop="answer56" label="answer56" width="100">
                        </el-table-column>
                        <el-table-column prop="answer57" label="answer57" width="100">
                        </el-table-column>
                        <el-table-column prop="answer58" label="answer58" width="100">
                        </el-table-column>
                        <el-table-column prop="answer59" label="answer59" width="100">
                        </el-table-column>
                        <el-table-column prop="answer60" label="answer60" width="100">
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
          tableData:[],
          currentPage:1,
          totalCount:1,
          pageSizes:[2,5,10,20,50,100],
          PageSize:20,
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
          this.tableData = res.data;
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
          this.currentPage=1
          this.load();
      },
      handleSizeChange(val) {
          this.PageSize=val
          this.currentPage=1
          this.load();

      },
      handleCurrentChange(val) {
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
  