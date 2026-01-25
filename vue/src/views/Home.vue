<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="Questionnaire Management" name="first">
      <el-tabs v-model="question" @tab-click="questionClick">
        <el-tab-pane label="Questionnaire One" name="question1">
          <el-row class="survey-list">
            <el-col v-for="item in surveyImages5" :key="item.id" :span="4">
              <div class="survey-item">
                <div class="survey-title">questionnaire{{ item.index + 1 }}</div>
                <el-link :href="`/#/survey5?survey=${ item.index + 1}`" target="_blank">
                  <img :src="require(`../images/survey5/(${ item.index + 1}).jpg`)" :width="300" :height="300"/>
                </el-link>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="Questionnaire Two" name="question2">
          <el-row class="survey-list">
            <el-col v-for="item in surveyImages6" :key="item.id" :span="4">
              <div class="survey-item">
                <div class="survey-title">questionnaire{{ item.index + 1 }}</div>
                <el-link :href="`/#/survey6?survey=${ item.index + 1}`" target="_blank">
                  <img :src="require(`../images/survey6/(${ item.index + 1}).jpg`)" :width="300" :height="300"/>
                </el-link>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="Questionnaire Three" name="question3">
          <el-row class="survey-list">
            <el-col v-for="item in surveyImages7" :key="item.id" :span="4">
              <div class="survey-item">
                <div class="survey-title">questionnaire{{ item.index + 1 }}</div>
                <el-link :href="`/#/survey7?survey=${ item.index + 1}`" target="_blank">
                  <img :src="require(`../images/survey7/1- (${ item.index + 1}).jpg`)" :width="300" :height="300"/>
                </el-link>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="Questionnaire Four" name="question4">
          <el-row class="survey-list">
            <el-col v-for="item in surveyImages8" :key="item.id" :span="4">
              <div class="survey-item">
                <div class="survey-title">questionnaire{{ item.index + 1 }}</div>
                <el-link :href="`/#/survey8?survey=${ item.index + 1}`" target="_blank">
                  <img :src="require(`../images/survey8/1- (${ item.index + 1}).jpg`)" :width="300" :height="300"/>
                </el-link>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="Questionnaire Five" name="question5">
          <el-row class="survey-list">
            <el-col v-for="item in surveyImages9" :key="item.id" :span="4">
              <div class="survey-item">
                <div class="survey-title">questionnaire{{ item.index + 1 }}</div>
                <el-link :href="`/#/survey9?survey=${ item.index + 1}`" target="_blank">
                  <img :src="require(`../images/survey9/(${ item.index + 1}).jpg`)" :width="300" :height="300"/>
                </el-link>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="Questionnaire Six" name="question6">
          <el-row class="survey-list">
            <el-col v-for="item in surveyImages10" :key="item.id" :span="4">
              <div class="survey-item">
                <div class="survey-title">questionnaire{{ item.index + 1 }}</div>
                <el-link :href="`/#/survey10?survey=${ item.index + 1}`" target="_blank">
                  <img :src="require(`../images/survey10/(${ item.index + 1}).jpg`)" :width="300" :height="300"/>
                </el-link>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-tab-pane>
    <el-tab-pane label="Questionnaire backend" name="second">
      <div>
        <el-row style="margin-left: 20px;margin-top: 20px;">
          <el-button type="primary" @click="report1">Download the Excel version of Questionnaire 1</el-button>
          <el-button type="primary" @click="report2">Download the Excel version of Questionnaire 2</el-button>
          <el-button type="primary" @click="report3">Download the Excel version of questionnaire three</el-button>
          <el-button type="primary" @click="reportAll">Download all questionnaires in Excel format</el-button>
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
    </el-tab-pane>
    <el-tab-pane label="data analysis" name="third">
      <div>
        <input type="file" @change="handleFileUpload"/>
        <button @click="triggerFileInput">Upload file</button>
        <input ref="fileInput" type="file" style="display:none;" @change="handleFileUpload"/>
        
      </div>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
  export default {
    data() {
      return {
        surveyImages5: Array.from({ length: 5 }, (_, index) => ({
          id: index + 1,
          index
        })),
        surveyImages6: Array.from({ length: 5 }, (_, index) => ({
          id: index + 400, 
          index: index + 400 
        })),
        surveyImages7: Array.from({ length: 5 }, (_, index) => ({
          id: index + 800, 
          index: index + 800
        })),
        surveyImages8: Array.from({ length: 5 }, (_, index) => ({
          id: index + 900, 
          index: index + 900 
        })),
        surveyImages9: Array.from({ length: 5 }, (_, index) => ({
          id: index + 1000, 
          index: index + 1000 
        })),
        surveyImages10: Array.from({ length: 5  }, (_, index) => ({
          id: index + 1400, 
          index: index + 1400 
        })),
        serverUrl: "127.0.0.1",
        activeName: 'first',
        question: 'question1',
        sjfx: 'MultivariateScale',
        tableData:[],
        currentPage:1,
        totalCount:1,
        pageSizes:[2,5,10,20,50,100],
        PageSize:20,
        answerUser: '',
        xdfxtableData: {
          "head": ["Indicator","Overall reliability", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{'Indicator': 'Cronbach a', 'Overall reliability': '0.916', Fidelity: '0.774', 'Semantic consistency': '0.763', Recognizability: '0.893', 'Overall quality': '0.813','User preference': '0.932',Diversity: '0.774'}]
        },
        ztjytableData: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'SD1.5', Fidelity: '0.006597', 'Semantic consistency': '0.022119', Recognizability: '0.015115', 'Overall quality': '0.170468','User preference': '0.193815',Diversity: '0.061766'},
                   {Row0: 'SDXL', Fidelity: '0.011712', 'Semantic consistency': '0.001799', Recognizability: '0.000036', 'Overall quality': '0.35105','User preference': '0.00647',Diversity: '0.274067'},
                   {Row0: 'Cogview 2', Fidelity: '0.01174', 'Semantic consistency': '0.000038', Recognizability: '0.101186', 'Overall quality': '0.35908','User preference': '0.071017',Diversity: '0.017819'},
                   {Row0: 'Real Image', Fidelity: '0.00084', 'Semantic consistency': '0.0', Recognizability: '0.004058', 'Overall quality': '0.024907','User preference': '0.06475',Diversity: 'NaN'}]
        },
        msxtjtableData: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'SD1.5', Fidelity: '3.30±0.91', 'Semantic consistency': '3.01±1.30', Recognizability: '4.01±0.64', 'Overall quality': '3.55±0.73','User preference': '2.84±0.83',Diversity: '3.70±0.87'},
                   {Row0: 'SDXL', Fidelity: '3.47±0.93', 'Semantic consistency': '3.02±1.51', Recognizability: '4.03±0.70', 'Overall quality': '3.76±0.67','User preference': '3.10±0.89',Diversity: '3.30±0.88'},
                   {Row0: 'Cogview 2', Fidelity: '2.37±0.88', 'Semantic consistency': '2.20±1.32', Recognizability: '2.97±0.90', 'Overall quality': '2.67±0.83','User preference': '2.00±0.69',Diversity: '3.85±0.83'},
                   {Row0: 'Real Image', Fidelity: '3.94±0.78', 'Semantic consistency': '2.27±1.60', Recognizability: '4.05±0.67', 'Overall quality': '3.77±0.82','User preference': '3.15±0.95',Diversity: '\\'}]
        },
        anovatableData: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'P value', Fidelity: '0.000', 'Semantic consistency': '0.000', Recognizability: '0.000', 'Overall quality': '0.000','User preference': '0.000',Diversity: '0.000'},
                   {Row0: 'F value', Fidelity: '56.031', 'Semantic consistency': '7.081', Recognizability: '52.368', 'Overall quality': '46.510','User preference': '39.711',Diversity: '46.510'}]
        },
        fidelitytableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.727', 'Cogview 2': '0.000', 'Real Image': '0.000'},
                   {Row0: 'SDXL', "SD": '0.727', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.001'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.000', 'SDXL': '0.001', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        semanticconsistencytableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.974', 'Cogview 2': '0.000', 'Real Image': '0.258'},
                   {Row0: 'SDXL', "SD": '0.974', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.245'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.005'},
                   {Row0: 'Real Image', "SD": '0.258', 'SDXL': '0.258', 'Cogview 2': '0.005', 'Real Image': '\\'}]
        },
        recognizabilitytableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.910', 'Cogview 2': '0.000', 'Real Image': '0.700'},
                   {Row0: 'SDXL', "SD": '0.910', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.785'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.700', 'SDXL': '0.785', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        userpreferencetableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.029', 'Cogview 2': '0.000', 'Real Image': '0.010'},
                   {Row0: 'SDXL', "SD": '0.029', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.683'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.010', 'SDXL': '0.683', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        overallqualitytableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.530', 'Cogview 2': '0.000', 'Real Image': '0.036'},
                   {Row0: 'SDXL', "SD": '0.029', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.871'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.036', 'SDXL': '0.871', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        diversitytableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.001', 'Cogview 2': '0.219'},
                   {Row0: 'SDXL', "SD": '0.001', 'SDXL': '\\', 'Cogview 2': '0.000'},
                   {Row0: 'Cogview 2', "SD": '0.219', 'SDXL': '0.000', 'Cogview 2': '\\'}]
        },
        friedmantableData: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'P value', Fidelity: '3', 'Semantic consistency': '4', Recognizability: '5', 'Overall quality': '6','User preference': '7',Diversity: '8'}]
        },
        fidelity1tableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        semanticconsistency1tableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        recognizability1tableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        userpreference1tableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        overallquality1tableData: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        diversitytable1Data: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        xdfxtableData1: {
          "head": ["Indicator","Overall reliability", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{'Indicator': 'Cronbach a', 'Overall reliability': '0.748', Fidelity: '/', 'Semantic consistency': '/', Recognizability: '/', 'Overall quality': '/','User preference': '/',Diversity: '/'}]
        },
        ztjytableData1: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'SD1.5', Fidelity: '0.000003', 'Semantic consistency': '0.0', Recognizability: '0.000751', 'Overall quality': '0.008813','User preference': '0.0',Diversity: '0.002059'},
                   {Row0: 'SDXL', Fidelity: '0.016682', 'Semantic consistency': '0.000009', Recognizability: '0.000003', 'Overall quality': '0.006013','User preference': '0.000017',Diversity: '0.0'},
                   {Row0: 'Cogview 2', Fidelity: '0.000346', 'Semantic consistency': '0.0', Recognizability: '0.000002', 'Overall quality': '0.002558','User preference': '0.00008',Diversity: '0.0'},
                   {Row0: 'Real Image', Fidelity: '0.001075', 'Semantic consistency': '0.007406', Recognizability: '0.000109', 'Overall quality': '0.008202','User preference': '0.000012',Diversity: 'NaN'}]
        },
        msxtjtableData1: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'SD1.5', Fidelity: '3.26±1.20', 'Semantic consistency': '2.79±1.37', Recognizability: '3.75±1.11', 'Overall quality': '3.52±1.15','User preference': '3.08±1.30',Diversity: '3.78±1.01'},
                   {Row0: 'SDXL', Fidelity: '3.32±1.20', 'Semantic consistency': '3.26±1.34', Recognizability: '4.07±0.86', 'Overall quality': '3.67±1.14','User preference': '3.26±1.22',Diversity: '3.15±1.18'},
                   {Row0: 'Cogview 2', Fidelity: '1.92±0.91', 'Semantic consistency': '2.33±1.26', Recognizability: '2.35±1.15', 'Overall quality': '2.08±1.07','User preference': '1.83±0.95',Diversity: '4.00±0.91'},
                   {Row0: 'Real Image', Fidelity: '3.87±1.05', 'Semantic consistency': '3.68±1.17', Recognizability: '4.10±0.86', 'Overall quality': '3.76±1.05','User preference': '3.27±1.18',Diversity: '\\'}]
        },
        anovatableData1: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'P value', Fidelity: '0.000', 'Semantic consistency': '0.000', Recognizability: '0.000', 'Overall quality': '0.000','User preference': '0.000',Diversity: '0.000'},
                   {Row0: 'F value', Fidelity: '57.140', 'Semantic consistency': '20.639', Recognizability: '67.873', 'Overall quality': '51.575','User preference': '34.980',Diversity: '18.069'}]
        },
        fidelitytableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.699', 'Cogview 2': '0.000', 'Real Image': '0.000'},
                   {Row0: 'SDXL', "SD": '0.699', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.000'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        semanticconsistencytableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.010', 'Cogview 2': '0.012', 'Real Image': '0.000'},
                   {Row0: 'SDXL', "SD": '0.010', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.021'},
                   {Row0: 'Cogview 2', "SD": '0.012', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.000', 'SDXL': '0.021', 'Cogview 2': '0.005', 'Real Image': '\\'}]
        },
        recognizabilitytableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.025', 'Cogview 2': '0.000', 'Real Image': '0.014'},
                   {Row0: 'SDXL', "SD": '0.025', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.833'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.014', 'SDXL': '0.833', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        userpreferencetableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.928', 'Cogview 2': '0.000', 'Real Image': '0.549'},
                   {Row0: 'SDXL', "SD": '0.029', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.993'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.549', 'SDXL': '0.993', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        overallqualitytableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.277', 'Cogview 2': '0.000', 'Real Image': '0.252'},
                   {Row0: 'SDXL', "SD": '0.029', 'SDXL': '\\', 'Cogview 2': '0.000', 'Real Image': '0.952'},
                   {Row0: 'Cogview 2', "SD": '0.000', 'SDXL': '0.000', 'Cogview 2': '\\', 'Real Image': '0.000'},
                   {Row0: 'Real Image', "SD": '0.252', 'SDXL': '0.952', 'Cogview 2': '0.000', 'Real Image': '\\'}]
        },
        diversitytableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2"],
          "data": [{Row0: 'SD1.5', "SD": '\\', 'SDXL': '0.000', 'Cogview 2': '0.135'},
                   {Row0: 'SDXL', "SD": '0.000', 'SDXL': '\\', 'Cogview 2': '0.000'},
                   {Row0: 'Cogview 2', "SD": '0.135', 'SDXL': '0.000', 'Cogview 2': '\\'}]
        },
        friedmantableData1: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'P value', Fidelity: '3', 'Semantic consistency': '4', Recognizability: '5', 'Overall quality': '6','User preference': '7',Diversity: '8'}]
        },
        fidelity1tableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        semanticconsistency1tableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        recognizability1tableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        userpreference1tableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        overallquality1tableData1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },
        diversitytable1Data1: {
          "head": ["Row0", "SD","SDXL", "Cogview 2", "Real Image"],
          "data": [{Row0: 'Model 1', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 2', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 3', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'},
                   {Row0: 'Model 4', 'SD': 'Row0', 'SDXL': 'Row1', 'Cogview 2': 'Row2', 'Real Image': 'Row3'}]
        },

        dxxzbfbtj: {
          "head": ["Row0", "Fidelity", "Semantic consistency", "Recognizability", "Overall quality", "User preference", "Diversity"],
          "data": [{Row0: 'SD 1.5', Fidelity: '11.00%', 'Semantic consistency': '18.00%', Recognizability: '18.00%', 'Overall quality': '25.00%','User preference': '18.00%',Diversity: '41.00%'},
                   {Row0: 'SDXL', Fidelity: '29.00%', 'Semantic consistency': '40.00%', Recognizability: '40.00%', 'Overall quality': '34.00%','User preference': '47.00%',Diversity: '16.00%'},
                   {Row0: 'Cogview 2', Fidelity: '9.00%', 'Semantic consistency': '6.00%', Recognizability: '6.00%', 'Overall quality': '2.00%','User preference': '6.00%',Diversity: '43.00%'},
                   {Row0: 'Real Image', Fidelity: '51.00%', 'Semantic consistency': '36.00%', Recognizability: '36.00%', 'Overall quality': '39.00%','User preference': '29.00%',Diversity: '\\'}]
        },
      };
    },
    created() {
      this.load();
    },
    methods: {
      handleFileUpload(event) {
        const file = event.target.files[0];
        if (file) {
          console.log('selected file:', file);
          
        }
      },
      triggerFileInput() {
        this.$refs.fileInput.click();
      },
      handleClick(tab, event) {
        console.log(tab, event);
      },
      questionClick(tab, event) {
        console.log(tab, event);
      },
      sjfxClick(tab, event){
        console.log(tab, event);
      },
      subtractColumns(row){
        return (new Date(row.answerTime).getTime() - new Date(row.answerBeginTime).getTime())/1000;
      },
      load(){
        this.request.get("/answer/findByPage?pageNum="+this.currentPage+"&pageSize="+this.PageSize+"&answerUser="+this.answerUser).then(res => {
          this.tableData = res.data;
          this.totalCount=res.total;
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

  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

  .el-tabs__item.is-active {
    color: #409EFF;
    font-size: 20px;
    font-weight: 400;
  }
</style>
