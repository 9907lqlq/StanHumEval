<template>
  <div style="height: 80%;padding-top: 50px;padding-left: 40px;">
    <!-- <div id="preloader" style="display:none">
        <img :src="require(`../images/survey6/${this.loadimage}.jpg`)"/>
    </div> -->
      <el-row>
          <el-col :span="24" style="font-size: 40px;color: #808080;font-weight: bold;">
            Single-Item Scale of Semantic Consistency
          </el-col>
          <!-- <el-col :span="24" style="color: #808080;font-size: 20px;">
            语义一致性的单项目量表
          </el-col> -->
          <el-col :span="24" style="color: #808080"><br></el-col>
          <el-col :span="24" style="color: #808080;font-size: 20px;">
            Note: Please choose the score that best fits the meaning of the question.
          </el-col>
          <!-- <el-col :span="24" style="color: #808080;font-size: 20px;">
            注意：请选择最符合问题描述的分数选项
          </el-col> -->
      </el-row>
      <el-progress :percentage="this.percentage" :show-text="false" style="width: 95%"></el-progress>
      <div style="height: 20px;"></div>
      <el-row>
          <el-col :span="24" style="font-size: 40px;font-weight: bold;">
            {{ title }}
          </el-col>
          <!-- <el-col :span="24">
            {{ title2 }}
          </el-col>
          <el-col :span="24">
            {{ title3 }}
          </el-col> -->
      </el-row>
      <el-row>
          <el-col :span="8">
            <div style="font-weight: 600;font-size: 20px;height: 26px;display: inline-block;width: 1500px;">
                {{ title5 }}
              </div>
              <div style="font-size: 20px;height: 26px;display: inline-block;width: 1500px;">
                  <!-- {{ title4 }} -->
              </div>
              <!-- <div class="grid-content bg-purple" style="margin-top: 20px">
                <transition name="fade">
                  <img v-if="showImage" :src="require(`../images/survey6/${image}.jpg`)" style="width: 500px;height: 500px;"/>
                </transition>
              </div> -->
              <div class="grid-content bg-purple" style="margin-top: 20px">
                  <img :src="require(`../images/survey6/${image}.jpg`)" style="width: 500px;height: 500px;"/>
              </div>
          </el-col>
          <el-col :span="15">
              <div class="block">
                <el-carousel ref="imgs" trigger="click" @change="carouselChange" indicator-position="none" arrow="always" height="500px" :autoplay="false" :style="{ marginTop: ['480', '580', '680', '780'].includes(surveyName) ? '100px' : '70px' }" style="margin-left: 30px;border: 1px solid #808080">
                   <el-carousel-item v-for="(item,index) in question" :key="index">
                        <div  style="margin-top: 60px; height: 60px;">
                          <span style="margin-left: 60px;height: 50px;display: inline-block;font-size: 30px;color: #FF8D1A;font-weight: bold;">{{ item.idView }}</span>
                          <!-- <span style="margin-left: 60px;height: 40px;width: 100%;display: inline-block;color: #FF8D1A;font-size: 25px;">{{ item.idViewZ }}</span> -->
                        </div>
                        <div style="margin-top: 50px;">
                          <!-- <el-button @click="qianjin(index)">前进</el-button> -->
                          <el-radio-group v-model="arr[index]">
                              <el-radio @change="agreeChange(index)" :label="item.select" :key="item.select" v-for="item in item.idSelect" style="width: 100%;margin-top: 15px;margin-left: 60px;color: black;font-size: 20px;">{{ item.answer }}</el-radio>
                          </el-radio-group>
                          <!-- <el-button @click="houtui(index)">后退</el-button> -->
                        </div>
                      </el-carousel-item>
                  </el-carousel>
              </div>
          </el-col>
      </el-row>
      <el-row>
          <el-col :span="24" class="button-container">
              <el-button type="primary" @click="addAnswer()" style="text-align: center;width: 400px;border-radius: 25px;font-size: 30px;">Submit   <span style="font-size: 20px;"></span></el-button>
          </el-col>
      </el-row>
      <el-dialog title="Please enter your name" :visible.sync="dialogTableVisible" :showClose="false" :close-on-click-modal="false">
        <el-row>
          <el-input placeholder="Please enter your name"  v-model="username"></el-input>
        </el-row>
        <el-row type="flex" justify="center" align="middle">
          <el-button type="primary" @click="namesumbit">submit</el-button>
        </el-row>
      </el-dialog>
      <el-dialog title="提示" :visible.sync="dialogTableVisible2" width="30%">
        <span>网络故障，提交错误，或者用户名已经填写过该问卷</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="dialogTableVisible2 = false">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="提示" :visible.sync="dialogTableVisible3" width="30%">
        <span>网络故障，提交错误，请刷新页面重新填写</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible3 = false">取 消</el-button>
          <el-button type="primary" @click="dialogTableVisible3 = false">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="提示" :visible.sync="dialogTableVisible4" width="30%">
        <span>你还有题目({{ nosubmit }})未提交，请检查</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible4 = false">取 消</el-button>
          <el-button type="primary" @click="dialogTableVisible4 = false">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="提示" :visible.sync="dialogTableVisible5" width="30%">
        <span>请填写用户名</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible5 = false">取 消</el-button>
          <el-button type="primary" @click="dialogTableVisible5 = false">确 定</el-button>
        </span>
      </el-dialog>
  </div>
</template>

<script>  
export default {
  data () {
    return {
      title: '1.Semantic Consistency',
      title2: 'Definition：Semantic consistency is the degree to which the image matches the content of the text.',
      title3: '定义：语义一致性是指图像与文本内容的匹配程度',
      title4: 'A woman who is skateboarding down the street.',
      title5: '一个女人正在街上滑板',
      image: '',
      loadimage: '',
      question: [
        {id: '0', idView: "Q：This image matches the text description.", idViewZ: "问：这张图与文本描述匹配", idSelect: [{select: '1A',answer: "1. Strongly disagree."},{select: '1B',answer: "2. Disagree."},{select: '1C',answer: "3. Neither agree nor disagree."},{select: '1D',answer: "4. Agree."},{select: '1E',answer: "5. Strongly agree."}]},
        ],
      arr: [],
      answer: {
        answerUser: '',
        surveyName: '',
      },
      dialogTableVisible: true,
      dialogTableVisible2: false,
      dialogTableVisible3: false,
      dialogTableVisible4: false,
      dialogTableVisible5: false,
      username: '',
      surveyName: '',
      nosubmit: '',
      percentage: 0,
      showImage: true,
      showSubmit: false,
    };
  },
  methods: {
      handleChange(){
        this.showImage = true
        // this.$message('所有题目已完成，请提交');
        this.$alert('所有题目已完成，请提交', {
          confirmButtonText: '确定',
        });
        setTimeout(() => {
          this.showImage = false;
        },1000)
        this.showSubmit = true
      },
      agreeChange(index){
        // this.$refs.imgs.next();
        if(index == 1) {
          this.showImage = true
          // this.$message('所有题目已完成，请提交');
          this.$alert('所有题目已完成，请提交', {
            confirmButtonText: '确定',
          });
          setTimeout(() => {
            this.showImage = false;
          },1000)
          this.showSubmit = true
        } else {
          this.$refs.imgs.next();
        }
      },
      
      carouselChange(now) {
        this.showImage = true
        setTimeout(() => {
          this.showImage = false;
        },1000)

        this.percentage = parseInt(now) * 100/1;
        
        if (now == '0') {
          this.title = '1.Semantic Consistency'
          this.title2 = 'Definition：Semantic consistency is the degree to which the image matches the content of the text.'
          this.title3 = '定义：语义一致性是指图像与文本内容的匹配程度'
          this.image = '(' + this.surveyName + ')'
        } else if(now == '1') {
          this.title = '2. Fidelity'
          this.title2 = 'Definition：Fidelity is the degree of realism in an image.'
          this.title3 = '定义：真实性是指图像的逼真度'
          this.image = '(' + this.surveyName + ')'
        }
      },
      addAnswer(){
        for(var k = 0; k < 1; k++){
          if(this.arr[k] == undefined) {
            this.nosubmit = k+1
            this.dialogTableVisible4 = true
            return false
          }
        }
        for (var i = 0; i < this.arr.length; i++) {
          this.answer['answer' + (i + 1)] = this.arr[i];
        }
        this.request.post("/answer/insertAnswer",this.answer).then(res => {
          if (res == true){
            this.request.post("/survey/insertSurvey",{surveyName: this.surveyName,username: this.username}).then(res => {
              if (res == true){
                this.$router.push('/last');
              } else{
                this.dialogTableVisible3 = true;
              }
            })
          } else{
            this.dialogTableVisible3 = true;
          }
        })
      },
      namesumbit(){
        if(this.username == '') { 
          this.dialogTableVisible5 = true;
          return false;
        }
        this.request.post("/answer/insertAnswerUsername?username="+this.username+"&surveyName="+this.surveyName).then(res => {
          if (res == true){
            this.answer.answerUser = this.username
            this.answer.surveyName = this.surveyName
            this.dialogTableVisible = false
          } else {
            this.dialogTableVisible2 = true
          }
        })
      },
      // format(percentage) {
      //   return percentage === 100 ? '满' : `${percentage}%`;
      // }
  },
  created() {
    this.surveyName= this.$route.query.survey
    this.image = '(' + this.surveyName + ')'
    // this.loadimage = "1- (" + this.surveyName + ")"

    if (this.surveyName == '401' || this.surveyName == '601' || this.surveyName == '801' || this.surveyName == '1001') {
        this.title4 = '一名男子骑在一辆由两头牛拉着的木车上';
        this.title5 = 'Man rides on the back of a wooden cart being pulled by two cows.';
    }

    if (this.surveyName == '402' || this.surveyName == '602' || this.surveyName == '802' || this.surveyName == '1002') {
        this.title4 = '一位动物园管理员正在观察笼中大象的背部';
        this.title5 = 'A zoo keeper looking at the back of a caged elephant.';
    }

    if (this.surveyName == '403' || this.surveyName == '603' || this.surveyName == '803' || this.surveyName == '1003') {
        this.title4 = '一只猫坐在手提箱里的书籍和衣服中间';
        this.title5 = 'A cat sitting amongst books and clothing in a suitcase.';
    }

    if (this.surveyName == '404' || this.surveyName == '604' || this.surveyName == '804' || this.surveyName == '1004') {
        this.title4 = '一只大猫坐在沙发扶手上，旁边是一个正在使用电脑的女孩';
        this.title5 = 'A large cat sits on the sofa arm next to a girl using a computer.';
    }

    if (this.surveyName == '405' || this.surveyName == '605' || this.surveyName == '805' || this.surveyName == '1005') {
        this.title4 = '三个人在水泥地上和一大群人面前玩滑板';
        this.title5 = 'Three people skateboarding with cement and a large crowd.';
    }

    if (this.surveyName == '406' || this.surveyName == '606' || this.surveyName == '806' || this.surveyName == '1006') {
        this.title4 = '四个男人靠着一堵砖墙站着，其中一个人从食物容器里吃东西，另一个人嘴里塞了几根胡萝卜，另外两个人站在他们旁边';
        this.title5 = 'Four men standing against a brick wall with one of them eating from a food container, one with several carrots stuffed into his mouth, and the other two standing next to them.';
    }

    if (this.surveyName == '407' || this.surveyName == '607' || this.surveyName == '807' || this.surveyName == '1007') {
        this.title4 = '一大群滑雪者聚集在一片很大的雪场上';
        this.title5 = 'A big bunch of skiers in a very big snowy field.';
    }

    if (this.surveyName == '408' || this.surveyName == '608' || this.surveyName == '808' || this.surveyName == '1008') {
        this.title4 = '两匹小马站在一片长满青草的田野里';
        this.title5 = 'Two small horses standing in a field covered with grass.';
    }

    if (this.surveyName == '409' || this.surveyName == '609' || this.surveyName == '809' || this.surveyName == '1009') {
        this.title4 = '公园里两名年轻女子正在玩飞盘特技';
        this.title5 = 'Two young woman in a park doing tricks with a frisbee.';
    }

    if (this.surveyName == '410' || this.surveyName == '610' || this.surveyName == '810' || this.surveyName == '1010') {
        this.title4 = '这位年轻人正和他的宠物狗坐在车里学习课本';
        this.title5 = 'The young man is sitting in his car with his pet dog and studying from a textbook.';
    }

    if (this.surveyName == '411' || this.surveyName == '611' || this.surveyName == '811' || this.surveyName == '1011') {
        this.title4 = '四只穿着粉红色衣服的毛绒泰迪熊一起摆姿势';
        this.title5 = 'Four stuffed teddy bears dressed in pink and posed together.';
    }

    if (this.surveyName == '412' || this.surveyName == '612' || this.surveyName == '812' || this.surveyName == '1012') {
        this.title4 = '蓝色容器里装着苹果，纸箱里装着香蕉';
        this.title5 = 'Apples inside a blue container and a cardboard box of bananas.';
    }

    if (this.surveyName == '413' || this.surveyName == '613' || this.surveyName == '813' || this.surveyName == '1013') {
        this.title4 = '背景中，一座灯塔矗立在一个岛上，一名冲浪者站在橙色冲浪板上，手握黑色冲浪杆，乘风破浪';
        this.title5 = 'A lighthouse stands on an island in the background as a surfer on an orange board rides the surf while holding a black pole.';
    }

    if (this.surveyName == '414' || this.surveyName == '614' || this.surveyName == '814' || this.surveyName == '1014') {
        this.title4 = '两个人站在一座非常古老的建筑物外面';
        this.title5 = 'Two people stand outside of a very old building.';
    }

    if (this.surveyName == '415' || this.surveyName == '615' || this.surveyName == '815' || this.surveyName == '1015') {
        this.title4 = '特写镜头：电脑键盘和鼠标，背景是工人';
        this.title5 = 'a closeup of a computer keyboard and mouse with workers in the background';
    }

    if (this.surveyName == '416' || this.surveyName == '616' || this.surveyName == '816' || this.surveyName == '1016') {
        this.title4 = '几个人的特写镜头，其中一个人拿着伞';
        this.title5 = 'A close up of several people with one holding an umbrella.';
    }

    if (this.surveyName == '417' || this.surveyName == '617' || this.surveyName == '817' || this.surveyName == '1017') {
        this.title4 = '阳光明媚的日子，两匹马站在空旷的田野里';
        this.title5 = 'Two horses standing in an open field on a sunny day.';
    }

    if (this.surveyName == '418' || this.surveyName == '618' || this.surveyName == '818' || this.surveyName == '1018') {
        this.title4 = '一个小男孩盯着一整块披萨，做出一副傻乎乎的表情';
        this.title5 = 'A young boy is making a silly face while staring at a whole pizza.';
    }

    if (this.surveyName == '419' || this.surveyName == '619' || this.surveyName == '819' || this.surveyName == '1019') {
        this.title4 = '这位网球运动员对于这一高难度击球充满信心';
        this.title5 = 'The tennis player is confident about the difficult shot.';
    }

    if (this.surveyName == '420' || this.surveyName == '620' || this.surveyName == '820' || this.surveyName == '1020') {
        this.title4 = '鹅卵石街道的人行道上挤满了撑着伞的人';
        this.title5 = 'a cobblestone street has busy sidewalks full of people with umbrellas';
    }

    if (this.surveyName == '421' || this.surveyName == '621' || this.surveyName == '821' || this.surveyName == '1021') {
        this.title4 = '一名戴着花环的男子坐在满屋子的人中间';
        this.title5 = 'a man wearing a lei seated in a room full of people';
    }

    if (this.surveyName == '422' || this.surveyName == '622' || this.surveyName == '822' || this.surveyName == '1022') {
        this.title4 = '一位老人正在露天市场检查一些香蕉';
        this.title5 = 'an older man is inspecting some bananas at an outdoor market.';
    }

    if (this.surveyName == '423' || this.surveyName == '623' || this.surveyName == '823' || this.surveyName == '1023') {
        this.title4 = '一位女士坐在桌边，旁边有人正在往酒杯里倒一瓶酒';
        this.title5 = 'a woman is sitting at a table with a person pouring a bottle of wine into a wine glass.';
    }

    if (this.surveyName == '424' || this.surveyName == '624' || this.surveyName == '824' || this.surveyName == '1024') {
        this.title4 = '一男一女坐在木桌旁，桌上摆着香槟和蛋糕';
        this.title5 = 'a man and woman sitting by a wooden table with champagne and cake.';
    }

    if (this.surveyName == '425' || this.surveyName == '625' || this.surveyName == '825' || this.surveyName == '1025') {
        this.title4 = '三个人坐在一张圆形木桌旁，享用食物';
        this.title5 = 'three people seated at a round wooden table with food.';
    }

    if (this.surveyName == '426' || this.surveyName == '626' || this.surveyName == '826' || this.surveyName == '1026') {
        this.title4 = '一名男子坐在摆满食物的桌子前，用玻璃杯喝酒';
        this.title5 = 'a man drinking from a glass while sitting in front of a table full of food';
    }

    if (this.surveyName == '427' || this.surveyName == '627' || this.surveyName == '827' || this.surveyName == '1027') {
        this.title4 = '一名男子站在镜子前调整领带';
        this.title5 = 'a man standing in front of a mirror adjusting his tie';
    }

    if (this.surveyName == '428' || this.surveyName == '628' || this.surveyName == '828' || this.surveyName == '1028') {
        this.title4 = '两个男人站在带有木质橱柜的厨房里';
        this.title5 = 'two men stand in a kitchen with wooden cabinets.';
    }

    if (this.surveyName == '429' || this.surveyName == '629' || this.surveyName == '829' || this.surveyName == '1029') {
        this.title4 = '一个穿制服的男孩背对篮筐，手里拿着一个篮球';
        this.title5 = 'A uniformed boy is holding a basketball with his back to the hoop.';
    }

    if (this.surveyName == '430' || this.surveyName == '630' || this.surveyName == '830' || this.surveyName == '1030') {
        this.title4 = '一辆海滩巡逻吉普车沿着沙滩行驶，人们在浅水中嬉戏并放风筝';
        this.title5 = 'A beach patrol jeep drives along the sand as people wade and fly kites down the beach.';
    }

    if (this.surveyName == '431' || this.surveyName == '631' || this.surveyName == '831' || this.surveyName == '1031') {
        this.title4 = '一个小女孩拿着录音器站在桌子旁，桌上有未完成的画作';
        this.title5 = 'Small girl holding a recorder near a table with an unfinished drawing.';
    }

    if (this.surveyName == '432' || this.surveyName == '632' || this.surveyName == '832' || this.surveyName == '1032') {
        this.title4 = '一只猫站在电视机前的桌子上';
        this.title5 = 'A cat standing on a table in front of a TV.';
    }

    if (this.surveyName == '433' || this.surveyName == '633' || this.surveyName == '833' || this.surveyName == '1033') {
        this.title4 = '一只猫坐在毯子上，毯子旁边有一台笔记本电脑';
        this.title5 = 'A cat sitting on a blanket next to a laptop.';
    }

    if (this.surveyName == '434' || this.surveyName == '634' || this.surveyName == '834' || this.surveyName == '1034') {
        this.title4 = '一个小男孩站在一堆行李箱旁边';
        this.title5 = 'A little boy stands by a stack of suitcases.';
    }

    if (this.surveyName == '435' || this.surveyName == '635' || this.surveyName == '835' || this.surveyName == '1035') {
        this.title4 = '两只长颈鹿站在动物园的围栏后面';
        this.title5 = 'Two giraffes stand behind a fence in a zoo enclosure.';
    }

    if (this.surveyName == '436' || this.surveyName == '636' || this.surveyName == '836' || this.surveyName == '1036') {
        this.title4 = '两个图像：一个女人和一只猫在窗前，另一个是有施工标志的高速公路';
        this.title5 = 'Two images: A woman and a cat at a window and a highway with a construction sign.';
    }

    if (this.surveyName == '437' || this.surveyName == '637' || this.surveyName == '837' || this.surveyName == '1037') {
        this.title4 = '这三只鸟在海滩上寻找食物';
        this.title5 = 'These three birds are walking along the beach looking for food.';
    }

    if (this.surveyName == '438' || this.surveyName == '638' || this.surveyName == '838' || this.surveyName == '1038') {
        this.title4 = '一头大象在泥路上跟在一辆车后面走';
        this.title5 = 'A large elephant walking behind a car on a dirt road.';
    }

    if (this.surveyName == '439' || this.surveyName == '639' || this.surveyName == '839' || this.surveyName == '1039') {
        this.title4 = '一个小女孩坐在人行道上用手机';
        this.title5 = 'A young girl sitting on the sidewalk using her phone.';
    }

    if (this.surveyName == '440' || this.surveyName == '640' || this.surveyName == '840' || this.surveyName == '1040') {
        this.title4 = '一个年轻男子拿着网球拍站在球场上';
        this.title5 = 'A young man holding a tennis racquet on a court.';
    }

    if (this.surveyName == '441' || this.surveyName == '641' || this.surveyName == '841' || this.surveyName == '1041') {
        this.title4 = '一个田野里，背景有三头牛在吃草';
        this.title5 = 'A field with three cows grazing in the background.';
    }

    if (this.surveyName == '442' || this.surveyName == '642' || this.surveyName == '842' || this.surveyName == '1042') {
        this.title4 = '几个人在机场看着一堆行李';
        this.title5 = 'Multiple men looking at a stack of luggage at an airport.';
    }

    if (this.surveyName == '443' || this.surveyName == '643' || this.surveyName == '843' || this.surveyName == '1043') {
        this.title4 = '两匹马在围栏的草地上奔跑';
        this.title5 = 'Two horses running inside a grassy fenced pasture.';
    }

    if (this.surveyName == '444' || this.surveyName == '644' || this.surveyName == '844' || this.surveyName == '1044') {
        this.title4 = '一群人牵着马穿过田野';
        this.title5 = 'A group of people walking horses through a field.';
    }

    if (this.surveyName == '445' || this.surveyName == '645' || this.surveyName == '845' || this.surveyName == '1045') {
        this.title4 = '几只鸟在日落时分走过海岸';
        this.title5 = 'Several birds walk across the shore as the sun sets.';
    }

    if (this.surveyName == '446' || this.surveyName == '646' || this.surveyName == '846' || this.surveyName == '1046') {
        this.title4 = '两个孩子坐着，其中一个看起来有些痛苦';
        this.title5 = 'Two children are sitting and one appears to be in pain.';
    }

    if (this.surveyName == '447' || this.surveyName == '647' || this.surveyName == '847' || this.surveyName == '1047') {
        this.title4 = '两个微笑的男人在厨房里准备热狗面包';
        this.title5 = 'Two smiling men prepare hot dog rolls in a kitchen.';
    }

    if (this.surveyName == '448' || this.surveyName == '648' || this.surveyName == '848' || this.surveyName == '1048') {
        this.title4 = '一位年轻人坐在一头大象上面。';
        this.title5 = 'a young man who is sitting on top of very large elephant.';
    }

    if (this.surveyName == '449' || this.surveyName == '649' || this.surveyName == '849' || this.surveyName == '1049') {
        this.title4 = '一名女子正准备用球拍击打网球，一群观众在看台上观看。';
        this.title5 = 'a woman getting ready to hit a tennis ball with a racket while a group of people watch from the stands.';
    }

    if (this.surveyName == '450' || this.surveyName == '650' || this.surveyName == '850' || this.surveyName == '1050') {
        this.title4 = '一大群鸟站在停车场靠近汽车的空地上。';
        this.title5 = 'a large group of birds are standing in the parking lot in empty spaces near cars.';
    }

    if (this.surveyName == '451' || this.surveyName == '651' || this.surveyName == '851' || this.surveyName == '1051') {
        this.title4 = '一名妇女和带着行李的孩子们的铜像';
        this.title5 = 'a bronze statue of a woman and children with luggage';
    }

    if (this.surveyName == '452' || this.surveyName == '652' || this.surveyName == '852' || this.surveyName == '1052') {
        this.title4 = '一只灰白相间的猫靠在一台小电视上。';
        this.title5 = 'a gray and white cat leaning up against a small tv.';
    }

    if (this.surveyName == '453' || this.surveyName == '653' || this.surveyName == '853' || this.surveyName == '1053') {
        this.title4 = '一个女孩休息时捂着额头，握着网球拍。';
        this.title5 = 'a girl holding her brow and her tennis racquet as she takes a break.';
    }

    if (this.surveyName == '454' || this.surveyName == '654' || this.surveyName == '854' || this.surveyName == '1054') {
        this.title4 = '坐在城市建筑上打电话的年轻女性的修改照片';
        this.title5 = 'altered photograph of young women on cell phones sitting on city buildings';
    }

    if (this.surveyName == '455' || this.surveyName == '655' || this.surveyName == '855' || this.surveyName == '1055') {
        this.title4 = '一辆救世军的卡车，人们站在车周围，从卡车上取饮料。';
        this.title5 = 'a salvation army truck with people standing around the vehicle and getting beverages from the truck.';
    }

    if (this.surveyName == '456' || this.surveyName == '656' || this.surveyName == '856' || this.surveyName == '1056') {
        this.title4 = '一座大钟悬挂在两个人行走的平台上方。';
        this.title5 = 'a big clock hangs above a platform that two people are walking on.';
    }

    if (this.surveyName == '457' || this.surveyName == '657' || this.surveyName == '857' || this.surveyName == '1057') {
        this.title4 = '一张模糊的照片，人们在雨中撑着伞走在街上。';
        this.title5 = 'a blurry picture of people walking on the street in the rain with their umbrellas.';
    }

    if (this.surveyName == '458' || this.surveyName == '658' || this.surveyName == '858' || this.surveyName == '1058') {
        this.title4 = '一群人站在那里看着一个女人切一块蛋糕，桌子上摆着一堆咖啡杯和碟子。';
        this.title5 = 'a bunch of people are standing around watching a woman cut a piece of cake and there are a bunch of coffee cups and saucers on the table.';
    }

    if (this.surveyName == '459' || this.surveyName == '659' || this.surveyName == '859' || this.surveyName == '1059') {
        this.title4 = '一个正在航行的蜂蜜烤坚果的男人和在雨中撑着伞走在街上的人们';
        this.title5 = 'a man that is sailing honey-roasted nuts and people walking down the street with their umbrellas in the rain';
    }

    if (this.surveyName == '460' || this.surveyName == '660' || this.surveyName == '860' || this.surveyName == '1060') {
        this.title4 = '一名男子撑开一把彩色雨伞，另一人则将脚伸进雨伞。';
        this.title5 = 'a man is holding a colorful umbrella open while another person sticks his feet into it.';
    }

    if (this.surveyName == '461' || this.surveyName == '661' || this.surveyName == '861' || this.surveyName == '1061') {
        this.title4 = '一位老年男子和一位老年妇女坐在繁忙的人行道上的长椅上。';
        this.title5 = 'an elderly man and woman sit on a bench on a busy sidewalk.';
    }

    if (this.surveyName == '462' || this.surveyName == '662' || this.surveyName == '862' || this.surveyName == '1062') {
        this.title4 = '繁忙的街道两旁，人潮涌动。';
        this.title5 = 'a large number of people walk along all sides of a busy street.';
    }

    if (this.surveyName == '463' || this.surveyName == '663' || this.surveyName == '863' || this.surveyName == '1063') {
        this.title4 = '一只猫坐在停在窗户下的踏板车上';
        this.title5 = 'A cat sitting on a scooter parked below a window.';
    }

    if (this.surveyName == '464' || this.surveyName == '664' || this.surveyName == '864' || this.surveyName == '1064') {
        this.title4 = '一只孤独的狗被拴在一个长凳旁边';
        this.title5 = 'A lonely leashed dog sits beside a bench.';
    }

    if (this.surveyName == '465' || this.surveyName == '665' || this.surveyName == '865' || this.surveyName == '1065') {
        this.title4 = '两个女士坐着喝饮料，一个在抽烟，另一个坐在冰箱里';
        this.title5 = 'Two ladies seated with cups of drinks, one smoking while the other seated inside a fridge.';
    }

    if (this.surveyName == '466' || this.surveyName == '666' || this.surveyName == '866' || this.surveyName == '1066') {
        this.title4 = '两只长颈鹿从两个不同的房间彼此对视';
        this.title5 = 'Two giraffes looking at each other from two separate rooms.';
    }

    if (this.surveyName == '467' || this.surveyName == '667' || this.surveyName == '867' || this.surveyName == '1067') {
        this.title4 = '一个女人带着滑雪板和两只棕色的狗站在雪地里，看着镜头';
        this.title5 = 'A woman with skis and two tan dogs standing in the snow looking at the camera.';
    }

    if (this.surveyName == '468' || this.surveyName == '668' || this.surveyName == '868' || this.surveyName == '1068') {
        this.title4 = '两只鸟站在长颈鹿的背上';
        this.title5 = 'Two birds sitting on the back of a giraffe.';
    }

    if (this.surveyName == '469' || this.surveyName == '669' || this.surveyName == '869' || this.surveyName == '1069') {
        this.title4 = '雪天，一只猫坐在自行车座上，低头看着另一只猫';
        this.title5 = 'a cat sits on the seat of a bicycle and looks down at another cat on a snowy day';
    }

    if (this.surveyName == '470' || this.surveyName == '670' || this.surveyName == '870' || this.surveyName == '1070') {
        this.title4 = '一只猫坐在长凳上，旁边是一个乌龟形状的花盆';
        this.title5 = 'a cat sits on a bench near a flower pot shaped like a turtle.';
    }

    if (this.surveyName == '471' || this.surveyName == '671' || this.surveyName == '871' || this.surveyName == '1071') {
        this.title4 = '身穿黑色皮夹克和黑色牛仔裤的步行男子。全长工作室拍摄，白色背景';
        this.title5 = 'Walking man in black leather jacket and black jeans. Full length studio shot isolated on white.';
    }

    if (this.surveyName == '472' || this.surveyName == '672' || this.surveyName == '872' || this.surveyName == '1072') {
        this.title4 = '圣诞树前的喜庆情侣';
        this.title5 = 'Festive couple in front of Christmas tree.';
    }

    if (this.surveyName == '473' || this.surveyName == '673' || this.surveyName == '873' || this.surveyName == '1073') {
        this.title4 = '婴儿礼物太可爱了，在礼物上系一条丝带，再用衣夹夹上一些袜子，瞧';
        this.title5 = 'So Cute For Baby Gifts Tie A Ribbon Around The Gift And Attach Some Socks With Clothespins Voila.';
    }

    if (this.surveyName == '474' || this.surveyName == '674' || this.surveyName == '874' || this.surveyName == '1074') {
        this.title4 = '烧烤烤架上土豆的高角度视图';
        this.title5 = 'High Angle View Of Potatoes On Barbecue Grill.';
    }

    if (this.surveyName == '475' || this.surveyName == '675' || this.surveyName == '875' || this.surveyName == '1075') {
        this.title4 = '1991 年环法自行车赛期间，威尔弗里德·彼得斯推搡了一位身着世界冠军队服的调皮年轻粉丝';
        this.title5 = "Wilfried Peeters pushes a cheeky young fan dressed in the World Champion's colours, during the 1991 Tour de France.";
    }

    if (this.surveyName == '476' || this.surveyName == '676' || this.surveyName == '876' || this.surveyName == '1076') {
        this.title4 = '身穿红色衣服的小丑木偶操纵者。平面卡通风格';
        this.title5 = 'Puppeteer with clown puppet in red clothes. Raster illustration in flat cartoon style.';
    }

    if (this.surveyName == '477' || this.surveyName == '677' || this.surveyName == '877' || this.surveyName == '1077') {
        this.title4 = '伊拉·格拉斯微笑着望向远方的照片，他面前放着一个麦克风';
        this.title5 = 'A photo of Ira Glass smiling and looking into the distance. There is a microphone in front of him.';
    }

    if (this.surveyName == '478' || this.surveyName == '678' || this.surveyName == '878' || this.surveyName == '1078') {
        this.title4 = '黑森林蛋糕和鲜花';
        this.title5 = 'Black forest cake and flowers.';
    }

    if (this.surveyName == '479' || this.surveyName == '679' || this.surveyName == '879' || this.surveyName == '1079') {
        this.title4 = '快乐的两代人活跃的家庭在客厅里跳舞，无忧无虑的老祖父和可爱的学龄前孙子在家里一起听音乐、跳跃、享受时光';
        this.title5 = 'Happy two age generations active family dancing in living room, carefree old senior adult grandfather and cute preschool grandson having fun listening to music jumping enjoying time together at home.';
    }

    if (this.surveyName == '480' || this.surveyName == '680' || this.surveyName == '880' || this.surveyName == '1080') {
        this.title4 = '女运动员奔跑和跳跃。红色背景下健康的非洲女性锻炼的侧视图';
        this.title5 = 'Female athlete running and jumping. Side view shot of healthy african woman working out against red background.';
    }

    if (this.surveyName == '481' || this.surveyName == '681' || this.surveyName == '881' || this.surveyName == '1081') {
        this.title4 = '有小提琴的美丽的女孩，背景是白色';
        this.title5 = 'Beautiful young girl with violin, isolated on white.';
    }

    if (this.surveyName == '482' || this.surveyName == '682' || this.surveyName == '882' || this.surveyName == '1082') {
        this.title4 = '指着头上带着悲伤的生态纸袋的男人，全身';
        this.title5 = 'pointing man with sad ecological paper bag on head, full length.';
    }

    if (this.surveyName == '483' || this.surveyName == '683' || this.surveyName == '883' || this.surveyName == '1083') {
        this.title4 = '2017 年温彻斯特大教堂合唱团';
        this.title5 = 'Choir at Winchester Cathedral in 2017';
    }

    if (this.surveyName == '484' || this.surveyName == '684' || this.surveyName == '884' || this.surveyName == '1084') {
        this.title4 = '缅甸伊洛瓦底江畔沐浴的妇女';
        this.title5 = 'Women taking bath on irrawaddy river banks, Myanmar';
    }

    if (this.surveyName == '485' || this.surveyName == '685' || this.surveyName == '885' || this.surveyName == '1085') {
        this.title4 = '年轻的黑发商业女性在电脑后面有颈部疼痛';
        this.title5 = 'Young brunette business woman has pain neck behind computer — #20863283';
    }

    if (this.surveyName == '486' || this.surveyName == '686' || this.surveyName == '886' || this.surveyName == '1086') {
        this.title4 = '大绝妙普拉提动作';
        this.title5 = 'Top 5 Kickass Pilates Moves';
    }

    if (this.surveyName == '487' || this.surveyName == '687' || this.surveyName == '887' || this.surveyName == '1087') {
        this.title4 = '科瓦尔斯基正在思考国王朱利安';
        this.title5 = 'Kowalski is thinking about King Julien.';
    }

    if (this.surveyName == '488' || this.surveyName == '688' || this.surveyName == '888' || this.surveyName == '1088') {
        this.title4 = '购买库存照片——白天在户外坐在地上，无忧无虑地通过耳机听音乐的年轻女子';
        this.title5 = 'Buy stock photo Shot of a carefree young woman seated on the floor while listening to music through her earphones outside during the day.';
    }

    if (this.surveyName == '489' || this.surveyName == '689' || this.surveyName == '889' || this.surveyName == '1089') {
        this.title4 = '浅黄色上衣和裙子';
        this.title5 = 'Top and skirt Light yellow.';
    }

    if (this.surveyName == '490' || this.surveyName == '690' || this.surveyName == '890' || this.surveyName == '1090') {
        this.title4 = '女人在家用刷子刷墙，照片';
        this.title5 = 'woman paints wall with brush at home photo.';
    }

    if (this.surveyName == '491' || this.surveyName == '691' || this.surveyName == '891' || this.surveyName == '1091') {
        this.title4 = '哈兰县妇女主席 Lindsey Boggs 和青年农民主席 Nathan Boggs 为五年级学生授课';
        this.title5 = "Harlan County Women's Chair Lindsey Boggs and Young Farmer Chair Nathan Boggs teach 5th graders";
    }

    if (this.surveyName == '492' || this.surveyName == '692' || this.surveyName == '892' || this.surveyName == '1092') {
        this.title4 = '年轻的陶工在陶轮上旋转粘土';
        this.title5 = "Young potter spinning clay on the potter's wheel.";
    }

    if (this.surveyName == '493' || this.surveyName == '693' || this.surveyName == '893' || this.surveyName == '1093') {
        this.title4 = '女孩刷牙';
        this.title5 = 'girl brushing her teeth';
    }

    if (this.surveyName == '494' || this.surveyName == '694' || this.surveyName == '894' || this.surveyName == '1094') {
        this.title4 = '安娜·帕奎因与她的狗的照片';
        this.title5 = 'Photos of Anna Paquin With her Dogs';
    }

    if (this.surveyName == '495' || this.surveyName == '695' || this.surveyName == '895' || this.surveyName == '1095') {
        this.title4 = '侧平板支撑带手臂伸展';
        this.title5 = 'Side Plank with Arm Extension';
    }

    if (this.surveyName == '496' || this.surveyName == '696' || this.surveyName == '896' || this.surveyName == '1096') {
        this.title4 = '年轻夫妇驾驶汽车';
        this.title5 = 'Young couple driving car';
    }

    if (this.surveyName == '497' || this.surveyName == '697' || this.surveyName == '897' || this.surveyName == '1097') {
        this.title4 = '生活方式-犹他州凯恩维尔，日落时分，梅丽莎·哈格多恩在亨利山下练习瑜伽';
        this.title5 = 'Lifestyle-Melissa Hagedorn doing yoga below the Henry Mountains at sunset, Cainville, Utah';
    }

    if (this.surveyName == '498' || this.surveyName == '698' || this.surveyName == '898' || this.surveyName == '1098') {
        this.title4 = '在苏格兰边境的一家农场，萨福克杂交母羊正在接受羊羔扫描，以预测即将到来的产羔季节的出生率。';
        this.title5 = 'Falside, Chesters, Hawick, Scotland, UK. 17th January 2016. Suffolk cross ewes are scanned for lambs in order to forecast the birth rate for the forthcoming lambing season at a farm in the Scottish Borders.';
    }

    if (this.surveyName == '499' || this.surveyName == '699' || this.surveyName == '899' || this.surveyName == '1099') {
        this.title4 = '欧洲赛艇锦标赛';
        this.title5 = 'ROW-The European Rowing Championships Royalty Freegraphy';
    }

    if (this.surveyName == '500' || this.surveyName == '700' || this.surveyName == '900' || this.surveyName == '1100') {
        this.title4 = '越野摩托车在赛道上比赛';
        this.title5 = 'Motocross bikes racing in track —';
    }

    if (this.surveyName == '501' || this.surveyName == '701' || this.surveyName == '901' || this.surveyName == '1101') {
        this.title4 = '儿童艺术 - 查尔斯·伯顿·巴伯 (Charles Burton Barber) 创作的《女孩与狗》';
        this.title5 = 'Kid Art -Girl with Dogs by Charles Burton Barber';
    }

    if (this.surveyName == '502' || this.surveyName == '702' || this.surveyName == '902' || this.surveyName == '1102') {
        this.title4 = '震惊与敬畏-一位新娘在婚礼上看到亚当莱文站在台上时惊呆了';
        this.title5 = "Shock and awe-A bride's jaw dropped upon seeing Adam Levine on stage at her wedding";
    }

    if (this.surveyName == '503' || this.surveyName == '703' || this.surveyName == '903' || this.surveyName == '1103') {
        this.title4 = '她可爱极了-由内布拉斯加州奥马哈的 Lacy Marie 摄影';
        this.title5 = '{She as cute as can be.} by Lacy Marie Photography in Omaha NE';
    }

    if (this.surveyName == '504' || this.surveyName == '704' || this.surveyName == '904' || this.surveyName == '1104') {
        this.title4 = '一群火烈鸟（红鹳）在地上进食';
        this.title5 = 'Group of flamingos (phoenicopterus) eating on the ground';
    }

    if (this.surveyName == '505' || this.surveyName == '705' || this.surveyName == '905' || this.surveyName == '1105') {
        this.title4 = '狮子如何重返世界上最严酷的沙漠之一';
        this.title5 = 'How lions returned to one of the harshest deserts in the world';
    }

    if (this.surveyName == '506' || this.surveyName == '706' || this.surveyName == '906' || this.surveyName == '1106') {
        this.title4 = '布罗克顿小学举行了品德教育大会。十六名学生被选为本月学生，代表幼儿园至五年级的学生。这些学生每天都表现出责任感的性格特征';
        this.title5 = 'Brocton Elementary School celebrated its character education assembly. Sixteen students were chosen as Students of the Month representing grades Kindergarten through 5.';
    }

    if (this.surveyName == '507' || this.surveyName == '707' || this.surveyName == '907' || this.surveyName == '1107') {
        this.title4 = '活跃的运动型狗狗在海边奔跑';
        this.title5 = 'Active athletic dog puppy running at the sea.';
    }

    if (this.surveyName == '508' || this.surveyName == '708' || this.surveyName == '908' || this.surveyName == '1108') {
        this.title4 = '一大群穿红、白、蓝色衣服的员工举起双手庆祝';
        this.title5 = 'Big group of employees in red, white, and blue raise their arms in celebration.';
    }

    if (this.surveyName == '509' || this.surveyName == '709' || this.surveyName == '909' || this.surveyName == '1109') {
        this.title4 = '肯尼亚马赛马拉国家保护区的非洲象';
        this.title5 = 'African Elephant in the Maasai Mara National Reserve, Kenya, Africa.';
    }

    if (this.surveyName == '510' || this.surveyName == '710' || this.surveyName == '910' || this.surveyName == '1110') {
        this.title4 = '发送信息以保护灰狼！';
        this.title5 = 'Send a message to protect the gray wolf!';
    }

    if (this.surveyName == '511' || this.surveyName == '711' || this.surveyName == '911' || this.surveyName == '1111') {
        this.title4 = '青春的照片——快乐的年轻漂亮女子与复古自行车';
        this.title5 = 'foto of adolescence -Happy young beautiful woman with retro bicycle.';
    }

    if (this.surveyName == '512' || this.surveyName == '712' || this.surveyName == '912' || this.surveyName == '1112') {
        this.title4 = '刺绣蕾丝雪纺衬衫，女士红色衬衫';
        this.title5 = 'Embroidery Lace Chiffon Blouse Red Shirt Women.';
    }

    if (this.surveyName == '513' || this.surveyName == '713' || this.surveyName == '913' || this.surveyName == '1113') {
        this.title4 = '一位漂亮的穿白色T恤的女孩微笑着摆出灵感手势，眼睛看向远方，灰色背景下的全景照片';
        this.title5 = 'Photo for happy beautiful girl in white t-shirt showing idea gesture and looking away isolated on grey, panoramic shot.';
    }

    if (this.surveyName == '514' || this.surveyName == '714' || this.surveyName == '914' || this.surveyName == '1114') {
        this.title4 = '无论 2012 年大选结果如何，约瑟夫表示印度都羡慕美国的两党制。数十个政党的数百名候选人参加竞选，他们的支持者骑着摩托车穿梭于各个城市游说选民';
        this.title5 = 'two-party system with envy. Hundreds of candidates from dozens of parties campaign, and their supporters ride through cities on motorbikes to lobby voters.';
    }

    if (this.surveyName == '515' || this.surveyName == '715' || this.surveyName == '915' || this.surveyName == '1115') {
        this.title4 = '后台 Calvin Klein Collection 2015 秋冬男装';
        this.title5 = 'Backstage Calvin Klein Collection Fall Winter 2015 Menswear';
    }

    if (this.surveyName == '516' || this.surveyName == '716' || this.surveyName == '916' || this.surveyName == '1116') {
        this.title4 = '一只野生雄性蓝眼蜻蜓栖息在美国加利福尼亚州因约主教县附近让·勒布朗路旁的一条运河旁的灌木丛上';
        this.title5 = 'a wild male blue-eyed darner rhionaeschna multicolor perches on a bush along a canal off jean leblanc road near bishop inyo county california united states';
    }

    if (this.surveyName == '517' || this.surveyName == '717' || this.surveyName == '917' || this.surveyName == '1117') {
        this.title4 = '三趾树懒在抓挠，巴拿马 - 树懒';
        this.title5 = 'Three-toed Sloth scratching, Panama -Sloth';
    }

    if (this.surveyName == '518' || this.surveyName == '718' || this.surveyName == '918' || this.surveyName == '1118') {
        this.title4 = '竞争激烈-性别专家认为，2004 年电影《贱女孩》中展现的女性行为刻薄，是因为缺少好男人';
        this.title5 = 'Competitive-The catty female behaviour, as displayed in 2004 film Mean Girls, is down to the lack of good men, a gender expert argues';
    }

    if (this.surveyName == '519' || this.surveyName == '719' || this.surveyName == '919' || this.surveyName == '1119') {
        this.title4 = 'BeX 与她的乐队在贝德福德现场表演';
        this.title5 = 'BeX live with her Band at The Bedford';
    }

    if (this.surveyName == '520' || this.surveyName == '720' || this.surveyName == '920' || this.surveyName == '1120') {
        this.title4 = '獒犬/大丹犬混种狗在弗吉尼亚州弗雷德里克斯堡等待领养 - 斯波特瑟尔韦尼亚收容所';
        this.title5 = 'Mastiff/Great Dane Mix Dog for adoption in Fredericksburg, Virginia -Spotsylvania Shelter.';
    }

    if (this.surveyName == '521' || this.surveyName == '721' || this.surveyName == '921' || this.surveyName == '1121') {
        this.title4 = '丛林健身器-小男孩在公园玩耍';
        this.title5 = 'jungle gym-Young boy playing at a park';
    }

    if (this.surveyName == '522' || this.surveyName == '722' || this.surveyName == '922' || this.surveyName == '1122') {
        this.title4 = '森林大象与水牛';
        this.title5 = 'Forest Elephant and Buffalo';
    }

    if (this.surveyName == '523' || this.surveyName == '723' || this.surveyName == '923' || this.surveyName == '1123') {
        this.title4 = '波士顿红袜队 11 号球员拉斐尔·德弗斯在马萨诸塞州波士顿芬威球场对阵纽约洋基队的比赛第二局中击出两分全垒打后与队友击掌庆祝';
        this.title5 = 'BOSTON, MA -AUGUST 18-Rafael Devers #11 of the Boston Red Sox high fives teammates after hitting a two run home run during the second inning of a game against the New York Yankees.';
    }

    if (this.surveyName == '524' || this.surveyName == '724' || this.surveyName == '924' || this.surveyName == '1124') {
        this.title4 = '一位女士面带笑容，望向远方。她长长的棕色头发在她身旁飘扬，身穿白色背心，肩带很粗。只能看到她的肩膀和脸';
        this.title5 = 'A woman looks off into the distance with a big smile on her face. Her long brown hair flows around her, and she wears a white tank top with thick straps. Only her shoulders and face are visible.';
    }

    if (this.surveyName == '525' || this.surveyName == '725' || this.surveyName == '925' || this.surveyName == '1125') {
        this.title4 = '一个女孩和她的马，日落海滩，瓦胡岛北岸';
        this.title5 = 'A Girl and Her Horse, Sunset Beach, North Shore of Oahu';
    }

    if (this.surveyName == '526' || this.surveyName == '726' || this.surveyName == '926' || this.surveyName == '1126') {
        this.title4 = '幼年大角猫头鹰';
        this.title5 = 'Juvenile Great Horned Owl';
    }

    if (this.surveyName == '527' || this.surveyName == '727' || this.surveyName == '927' || this.surveyName == '1127') {
        this.title4 = '一位居民跑过台风海马袭击后倒塌的加油站屋顶，地点是圣尼古拉斯';
        this.title5 = 'A resident runs past a collapsed roof of a petrol station after Typhoon Haima struck San Nicolas.';
    }

    if (this.surveyName == '528' || this.surveyName == '728' || this.surveyName == '928' || this.surveyName == '1128') {
        this.title4 = '海滩上的日落瑜伽';
        this.title5 = 'sunset yoga on the beach.';
    }

    if (this.surveyName == '529' || this.surveyName == '729' || this.surveyName == '929' || this.surveyName == '1129') {
        this.title4 = '混血老年人与年轻家庭成员在厨房里边做圣诞晚餐边聊天的特写，库存照片';
        this.title5 = 'Mixed race senior and young adult family members talking in the kitchen while preparing Christmas dinner together, close up Stockfoto.';
    }

    if (this.surveyName == '530' || this.surveyName == '730' || this.surveyName == '930' || this.surveyName == '1130') {
        this.title4 = 'Rosanio 摄影 | 新罕布什尔州本森公园孕妇摄影师';
        this.title5 = 'Rosanio Photography | Benson Park, New Hampshire Maternity Photographer.';
    }

    if (this.surveyName == '531' || this.surveyName == '731' || this.surveyName == '931' || this.surveyName == '1131') {
        this.title4 = '华盛顿州克拉克斯顿燕子公园的一群美洲反嘴鹬';
        this.title5 = 'Flock of American Avocets at Swallows Park in Clarkston, Washington.';
    }

    if (this.surveyName == '532' || this.surveyName == '732' || this.surveyName == '932' || this.surveyName == '1132') {
        this.title4 = '年轻漂亮的女孩骑着自行车，背景中有两个男人';
        this.title5 = 'Young beautiful girl on a bicycle with two men in the background Royalty Free Stock Image';
    }

    if (this.surveyName == '533' || this.surveyName == '733' || this.surveyName == '933' || this.surveyName == '1133') {
        this.title4 = '日间护理手指画烹饪';
        this.title5 = 'DAY CARE FINGER PAINT COOKING 021';
    }

    if (this.surveyName == '534' || this.surveyName == '734' || this.surveyName == '934' || this.surveyName == '1134') {
        this.title4 = '学生拉小提琴';
        this.title5 = 'A student plays violin';
    }

    if (this.surveyName == '535' || this.surveyName == '735' || this.surveyName == '935' || this.surveyName == '1135') {
        this.title4 = '吉尔吉斯斯坦首支女子曲棍球队的支持者在吉尔吉斯斯坦奥特拉德诺耶村的一场训练比赛中欢呼加油';
        this.title5 = "Supporters of Kyrgyzstan's first female hockey team cheer during a training match in the village of Otradnoye, Kyrgyzstan.";
    }

    if (this.surveyName == '536' || this.surveyName == '736' || this.surveyName == '936' || this.surveyName == '1136') {
        this.title4 = '德国普通秃鹰飞行';
        this.title5 = 'Kimball Stock Common Buzzard Flying In Germany';
    }

    if (this.surveyName == '537' || this.surveyName == '737' || this.surveyName == '937' || this.surveyName == '1137') {
        this.title4 = 'Ryan 假装玩耍并用巨型蜡笔蛋惊喜玩具学习颜色';
        this.title5 = 'Ryan Pretend Play and Learn Colors with Giant Crayons Egg Surprise Toys!';
    }

    if (this.surveyName == '538' || this.surveyName == '738' || this.surveyName == '938' || this.surveyName == '1138') {
        this.title4 = '快乐儿童与气球';
        this.title5 = 'Happy children with balloons — Stock fotografie';
    }

    if (this.surveyName == '539' || this.surveyName == '739' || this.surveyName == '939' || this.surveyName == '1139') {
        this.title4 = '女孩喝汤';
        this.title5 = 'Girl eating soup —';
    }

    if (this.surveyName == '540' || this.surveyName == '740' || this.surveyName == '940' || this.surveyName == '1140') {
        this.title4 = '节日狗狗芭蕾舞裙-绿色和红色';
        this.title5 = 'Holiday Dog Tutu  -Green and Red';
    }



  },
  mounted() {
    setTimeout(() => {
      this.showImage = false;
    },1000)
  }
};
// var list = document.getElementsByClassName('el-icon-arrow-right');
// list.onclick = testClick;
// for(var i in list){
//     // for循环给list对象数组的每个对象添加onclick单击事件,
//     // 绑定testClick函数.注意函数后面不能加空格.
//     list[i].onclick = testClick;
// }
// function testClick () {
//     console.log(this);
//     console.log(this.innerText);
// }
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.el-radio /deep/ .el-radio__label{
  font-size:20px !important;
}
/* .bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
} */
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.button-container{
  display: flex;
  justify-content: center;
}
/* .el-carousel__item:nth-child(2n) {
   background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
   background-color: #d3dce6;
} */
/* 定义过渡动画 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 1s; /* 调整时间来改变动画速度 */
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
.el-radio-button /deep/ .el-radio-button__inner {
  background-color: #F2F2F2 !important;
  padding-top: 35px;
  padding-bottom: 35px;
  padding-right: 25px;
  padding-left: 25px;
  margin-left: 20px;
  font-size: 30px;
  border-radius: 15px;
}
.el-radio-button /deep/ .el-radio-button__inner .left{
  background-color: #F2F2F2 !important;
}
</style>
