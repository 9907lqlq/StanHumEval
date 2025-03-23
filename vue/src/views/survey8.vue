<template>
  <div style="height: 80%;padding-top: 50px;padding-left: 40px;">
      <!-- <div id="preloader" style="display:none">
        <img :src="require(`../images/survey7/${this.loadimage1}.jpg`)"/>
        <img :src="require(`../images/survey7/${this.loadimage2}.jpg`)"/>
        <img :src="require(`../images/survey7/${this.loadimage3}.jpg`)"/>
        <img :src="require(`../images/survey7/${this.loadimage4}.jpg`)"/>
      </div> -->
      <el-row>
        <el-col :span="24" style="font-size: 40px;color: #808080;font-weight: bold;">
          Ranking Questionnaire of Semantic Consistency
        </el-col>
        <!-- <el-col :span="24" style="color: #808080;font-size: 20px;">
          语义一致性的排序问卷
        </el-col> -->
        <el-col :span="24" style="color: #808080"><br></el-col>
        <el-col :span="24" style="color: #808080;font-size: 20px;">
          Note: Please select the appropriate images to arrange in order.
        </el-col>
        <!-- <el-col :span="24" style="color: #808080;font-size: 20px;">
          注意：请选择适当的图像按顺序排列
        </el-col> -->
      </el-row>
      <el-progress :percentage="this.percentage" :show-text="false" style="width: 95%"></el-progress>
      <div style="height: 20px;"></div>
      <el-row>
        <el-col :span="24" style="font-size: 40px;font-weight: bold;">
          {{ title }}
        </el-col>
        <!-- <el-col :span="24">
          <br/>
        </el-col> 
        <el-col :span="24">
          {{ title2 }}
        </el-col>       
        <el-col :span="24">
          {{ title3 }}
        </el-col> -->
      </el-row>
      <el-row>
          <el-col :span="22">
              <div class="block">
                  <el-carousel ref="imgs" trigger="click" @change="carouselChange" indicator-position="none" arrow="always" height="630px" :autoplay="false" style="padding-top: 0px;" >
                      <el-carousel-item v-for="(item,index) in question" :key="index">
                          <span style="margin-top:0px;height: 50px;display: inline-block;font-size: 30px;color: #FF8D1A;font-weight: bold;">{{ item.idView }}</span>
                          <br>
                          <!-- <span style="height: 50px;width: 100%;display: inline-block;color: #FF8D1A;font-size: 20px;">{{ item.idViewZ }}</span> -->
                          <span style="font-weight: 600;text-align: center;display:block;font-size: 20px;">{{ title5 }}</span>
                          <!-- <span style="text-align: center;display:block;font-size: 20px;margin-bottom: 10px;">{{ title4 }}</span> -->
                          <span style="text-align: center;display:block;font-size: 20px;margin-bottom: 10px;"><br></span>
                          <el-radio-group style="margin-left: 60px;" ref="elradiogroup">
                            <el-radio :label="item.select" :key="item.select" v-for="(item,index1) in item.idSelect" style="padding: 0px" ref="elradio">
                                <img :src="require(`../images/survey8/${index1+1}- ${image}.jpg`)" style="width: 350px;height: 350px;" ref="myImage"/>
                                <br>
                                <el-input type="number" min="1" max="4" @blur="validateBlur(item.select,arr[item.select])" v-model="arr[item.select]" :key="item.select" style="width: 350px;margin-left: 10px;margin-top: 20px;" placeholder="Please enter 1-4 and sort them" />
                            </el-radio>
                          </el-radio-group>
                      </el-carousel-item>
                  </el-carousel>
              </div>
          </el-col>
      </el-row>
      <el-row>
          <el-col :span="24" class="button-container">
             <el-button type="primary" @click="addAnswer()" style="width: 400px;border-radius: 25px;font-size: 30px;">Submit   <span style="font-size: 20px;"></span></el-button>
          </el-col>
      </el-row>
      <el-dialog title="Please enter your name" :visible.sync="dialogTableVisible" :showClose="false" :close-on-click-modal="false">
        <el-row>
          <el-input placeholder="Please enter your name"  v-model="username"></el-input>
        </el-row>
        <el-row type="flex" justify="center" align="middle">
          <el-button type="primary" @click="namesumbit">Submit</el-button>
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
      <el-dialog title="提示" :visible.sync="dialogTableVisible6" width="30%">
        <span>格式错误，请填写1-4之间的数字</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible6 = false">取 消</el-button>
          <el-button type="primary" @click="dialogTableVisible6 = false">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="提示" :visible.sync="dialogTableVisible7" width="30%">
        <span>数字重复</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible7 = false">取 消</el-button>
          <el-button type="primary" @click="dialogTableVisible7 = false">确 定</el-button>
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
      title4: 'A decorative flower vase with lavender in it',
      title5: '一个装有薰衣草的装饰花瓶',
      image: '',
      loadimage1: '',
      loadimage2: '',
      loadimage3: '',
      loadimage4: '',
      question: [
        {id: 0, idView: "Q: Please rank the images based on how well they match the text description.",idViewZ:"问: 请根据图像与文本描述的匹配程度进行排序（1-4，1最匹配，4最不匹配）", idSelect: [{select: '1A',answer: "1.Completely unreal, clearly generated."},{select: '1B',answer: "2.Somewhat unreal, possibly a generated image."},{select: '1C',answer: "3.Neutral."},{select: '1D',answer: "4.Somewhat real, could be a real image."}]},
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
      dialogTableVisible6: false,
      dialogTableVisible7: false,
      username: '',
      surveyName: '',
      nosubmit: '',
      percentage: 0,
      showSubmit: false,
    };
  },
  methods: {
    validateBlur(key, val) {
      console.log(val)
      if(val !== undefined) {
        if (val !== null) {
          // 当输入框失去焦点时进行最终验证
          if(val < 1 || val > 4){
            this.arr[key] = ""
            this.dialogTableVisible6 = true
          }
          if (key == "1A") {
            if (this.arr["1B"] == val || this.arr["1C"] == val || this.arr["1D"] == val) {
              this.arr[key] = ""
              this.dialogTableVisible7 = true
            }
          } else if(key == "1B"){
            if (this.arr["1A"] == val || this.arr["1C"] == val || this.arr["1D"] == val) {
              this.arr[key] = ""
              this.dialogTableVisible7 = true
            }
          } else if(key == "1C") {
            if (this.arr["1A"] == val || this.arr["1B"] == val || this.arr["1D"] == val) {
              this.arr[key] = ""
              this.dialogTableVisible7 = true
            }
          } else if(key == "1D") {
            if (this.arr["1A"] == val || this.arr["1B"] == val || this.arr["1C"] == val) {
              this.arr[key] = ""
              this.dialogTableVisible7 = true
            }
          }
          // if (key == "2A") {
          //   if (this.arr["2B"] == val || this.arr["2C"] == val || this.arr["2D"] == val) {
          //     this.arr[key] = ""
          //     this.dialogTableVisible7 = true
          //   }
          // } else if(key == "2B"){
          //   if (this.arr["2A"] == val || this.arr["2C"] == val || this.arr["2D"] == val) {
          //     this.arr[key] = ""
          //     this.dialogTableVisible7 = true
          //   }
          // } else if(key == "2C") {
          //   if (this.arr["2A"] == val || this.arr["2B"] == val || this.arr["2D"] == val) {
          //     this.arr[key] = ""
          //     this.dialogTableVisible7 = true
          //   }
          // } else if(key == "2D") {
          //   if (this.arr["2A"] == val || this.arr["2B"] == val || this.arr["2C"] == val) {
          //     this.arr[key] = ""
          //     this.dialogTableVisible7 = true
          //   }
          // }
        }
      }
    },
    agreeChange(index){
        if(index == 1) {
          // this.$message('所有题目已完成，请提交');
          this.$alert('所有题目已完成，请提交', {
            confirmButtonText: '确定',
          });
          this.showSubmit = true
        } else {
          this.$refs.imgs.next();
        }
    },
    carouselChange(now) {
      this.percentage = parseInt(now) * 100/1;
      this.showSubmit = true
      // if (this.percentage == 100) {
      //   const ulElement = this.$refs.myImage[23];
      //   ulElement.style.display = 'none';

      //   // const ulElement2 = this.$refs.myImage[20];
      //   // ulElement2.style.marginLeft = '200px';

      //   // console.log(this.$refs.elradio[20].$refs.radio);
      //   // const ulElement3 = this.$refs.elradio[20].$refs.radio
      //   // ulElement3.style.marginLeft = '200px';
      //   const ulElement3 = this.$refs.elradiogroup[5].$children[0].$el
      //   ulElement3.style.marginLeft = '200px';
      //   // console.log(ulElement)
      //   // const ulElement = this.$el.querySelector('img'); // 获取ul元素
      //   // const fourthImageElement = ulElement.querySelector('li:nth-child(4) img'); // 获取第四个li元素的img元素
      //   // fourthImageElement.style.display = 'none'; // 设置img元素的display属性为'none'，隐藏图片
      //   this.percentage = 100
      // }
      // if (now == '0') {
      //   this.percentage = 0
      // } else if(now == '1') {
      //   this.percentage = 20
      // } else if(now == '2') {
      //   this.percentage = 40
      // } else if(now == '3') {
      //   this.percentage = 60
      // } else if(now == '4') {
      //   this.percentage = 80
      // } else if(now == '5') {
      //   const ulElement = this.$refs.myImage[23];
      //   ulElement.style.display = 'none';

      //   // const ulElement2 = this.$refs.myImage[20];
      //   // ulElement2.style.marginLeft = '200px';

      //   // console.log(this.$refs.elradio[20].$refs.radio);
      //   // const ulElement3 = this.$refs.elradio[20].$refs.radio
      //   // ulElement3.style.marginLeft = '200px';
      //   const ulElement3 = this.$refs.elradiogroup[5].$children[0].$el
      //   ulElement3.style.marginLeft = '200px';
      //   // console.log(ulElement)
      //   // const ulElement = this.$el.querySelector('img'); // 获取ul元素
      //   // const fourthImageElement = ulElement.querySelector('li:nth-child(4) img'); // 获取第四个li元素的img元素
      //   // fourthImageElement.style.display = 'none'; // 设置img元素的display属性为'none'，隐藏图片
      //   this.percentage = 100
      // }

      // if (now == '0') {
      //   this.title = '1. Fidelity（真实性）'
      //   this.title2 = 'Definition：Fidelity is the degree of realism in an image.'
      //   this.title3 = '定义：真实性是指图像的逼真度'
      //   this.image = '(' + this.surveyName + ')'
      // } else if(now == '1') {
      //   this.title = '2.Semantic Consistency（语义一致性）'
      //   this.title2 = 'Definition：Semantic consistency is the degree to which the image matches the content of the text.'
      //   this.title3 = '定义：语义一致性是指图像与文本内容的匹配程度'
      //   this.image = '(' + this.surveyName + ')'
      // } else if(now == '2') {
      //   this.title = '3. Recognizability（可识别性）'
      //   this.title2 = 'Definition：Recognizability is the degree to which the object described by the text can be accurately recognized in the image.'
      //   this.title3 = '定义：可识别性是指文本描述的物体在图像中可被准确识别的程度'
      //   this.image = '(' + this.surveyName + ')'
      // } else if(now == '3') {
      //   this.title = '4.Overall Quality（整体质量）'
      //   this.title2 = 'Definition：Overall quality is the comprehensive evaluation of the image as a whole.'
      //   this.title3 = '定义：整体质量是指对图像整体的综合评估'
      //   this.image = '(' + this.surveyName + ')'
      // } else if(now == '4') {
      //   this.title = '5. User preference（用户偏好）'
      //   this.title2 = "Definition：User preference is the degree and tendency of user's liking for the image."
      //   this.title3 = '定义：用户偏好是指用户对图像的喜好程度和倾向性'
      //   this.image = '(' + this.surveyName + ')'
      // } else if(now == '5') {
      //   this.title = '6. Diversity（多样性）'
      //   this.title2 = 'Definition：Diversity is the degree of difference and richness of variation between the generated images.'
      //   this.title3 = '定义：多样性是指图像之间的差异性及变化的丰富度'
      //   this.image = '1- (' + this.surveyName + ')'
      // }
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
      if(this.arr["1A"] == undefined || this.arr["1B"] == undefined || this.arr["1C"] == undefined || this.arr["1D"] == undefined) {
        this.nosubmit = 1
        this.dialogTableVisible4 = true
        return false
      }
      // if(this.arr["2A"] == undefined || this.arr["2B"] == undefined || this.arr["2C"] == undefined || this.arr["2D"] == undefined) {
      //   this.nosubmit = 2
      //   this.dialogTableVisible4 = true
      //   return false
      // }

      this.answer['answer1'] = this.arr["1A"];
      this.answer['answer2'] = this.arr["1B"];
      this.answer['answer3'] = this.arr["1C"];
      this.answer['answer4'] = this.arr["1D"];
      // this.answer['answer5'] = this.arr["2A"];
      // this.answer['answer6'] = this.arr["2B"];
      // this.answer['answer7'] = this.arr["2C"];
      // this.answer['answer8'] = this.arr["2D"];
    
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
  },
  created() {
    this.surveyName= this.$route.query.survey
    this.image = '(' + this.surveyName + ')'
    this.loadimage1 = "1- 1- (" + this.surveyName + ")"
    this.loadimage2 = "2- 1- (" + this.surveyName + ")"
    this.loadimage3 = "3- 1- (" + this.surveyName + ")"
    this.loadimage4 = "4- 1- (" + this.surveyName + ")"
    // 图片列表
    // const imagesToPreload = [ "1- 1- (" + this.surveyName + ").jpg",  "2- 1- (" + this.surveyName + ").jpg", "3- 1- (" + this.surveyName + ").jpg" ,"4- 1- (" + this.surveyName + ").jpg"];
    // imagesToPreload.forEach(imageUrl => {
    //   const img = new Image();
    //   img.src = imageUrl;
    // });
    if (this.surveyName == '901') {
        this.title4 = '一名男子骑在一辆由两头牛拉着的木车上';
        this.title5 = 'Man rides on the back of a wooden cart being pulled by two cows.';
    }

    if (this.surveyName == '902') {
        this.title4 = '一位动物园管理员正在观察笼中大象的背部';
        this.title5 = 'A zoo keeper looking at the back of a caged elephant.';
    }

    if (this.surveyName == '903') {
        this.title4 = '一只猫坐在手提箱里的书籍和衣服中间';
        this.title5 = 'A cat sitting amongst books and clothing in a suitcase.';
    }

    if (this.surveyName == '904') {
        this.title4 = '一只大猫坐在沙发扶手上，旁边是一个正在使用电脑的女孩';
        this.title5 = 'A large cat sits on the sofa arm next to a girl using a computer.';
    }

    if (this.surveyName == '905') {
        this.title4 = '三个人在水泥地上和一大群人面前玩滑板';
        this.title5 = 'Three people skateboarding with cement and a large crowd.';
    }

    if (this.surveyName == '906') {
        this.title4 = '四个男人靠着一堵砖墙站着，其中一个人从食物容器里吃东西，另一个人嘴里塞了几根胡萝卜，另外两个人站在他们旁边';
        this.title5 = 'Four men standing against a brick wall with one of them eating from a food container, one with several carrots stuffed into his mouth, and the other two standing next to them.';
    }

    if (this.surveyName == '907') {
        this.title4 = '一大群滑雪者聚集在一片很大的雪场上';
        this.title5 = 'A big bunch of skiers in a very big snowy field.';
    }

    if (this.surveyName == '908') {
        this.title4 = '两匹小马站在一片长满青草的田野里';
        this.title5 = 'Two small horses standing in a field covered with grass.';
    }

    if (this.surveyName == '909') {
        this.title4 = '公园里两名年轻女子正在玩飞盘特技';
        this.title5 = 'Two young woman in a park doing tricks with a frisbee.';
    }

    if (this.surveyName == '910') {
        this.title4 = '这位年轻人正和他的宠物狗坐在车里学习课本';
        this.title5 = 'The young man is sitting in his car with his pet dog and studying from a textbook.';
    }

    if (this.surveyName == '911') {
        this.title4 = '四只穿着粉红色衣服的毛绒泰迪熊一起摆姿势';
        this.title5 = 'Four stuffed teddy bears dressed in pink and posed together.';
    }

    if (this.surveyName == '912') {
        this.title4 = '蓝色容器里装着苹果，纸箱里装着香蕉';
        this.title5 = 'Apples inside a blue container and a cardboard box of bananas.';
    }

    if (this.surveyName == '913') {
        this.title4 = '背景中，一座灯塔矗立在一个岛上，一名冲浪者站在橙色冲浪板上，手握黑色冲浪杆，乘风破浪';
        this.title5 = 'A lighthouse stands on an island in the background as a surfer on an orange board rides the surf while holding a black pole.';
    }

    if (this.surveyName == '914') {
        this.title4 = '两个人站在一座非常古老的建筑物外面';
        this.title5 = 'Two people stand outside of a very old building.';
    }

    if (this.surveyName == '915') {
        this.title4 = '特写镜头：电脑键盘和鼠标，背景是工人';
        this.title5 = 'a closeup of a computer keyboard and mouse with workers in the background';
    }

    if (this.surveyName == '916') {
        this.title4 = '几个人的特写镜头，其中一个人拿着伞';
        this.title5 = 'A close up of several people with one holding an umbrella.';
    }

    if (this.surveyName == '917') {
        this.title4 = '阳光明媚的日子，两匹马站在空旷的田野里';
        this.title5 = 'Two horses standing in an open field on a sunny day.';
    }

    if (this.surveyName == '918') {
        this.title4 = '一个小男孩盯着一整块披萨，做出一副傻乎乎的表情';
        this.title5 = 'A young boy is making a silly face while staring at a whole pizza.';
    }

    if (this.surveyName == '919') {
        this.title4 = '这位网球运动员对于这一高难度击球充满信心';
        this.title5 = 'The tennis player is confident about the difficult shot.';
    }

    if (this.surveyName == '920') {
        this.title4 = '鹅卵石街道的人行道上挤满了撑着伞的人';
        this.title5 = 'a cobblestone street has busy sidewalks full of people with umbrellas';
    }

    if (this.surveyName == '921') {
        this.title4 = '一名戴着花环的男子坐在满屋子的人中间';
        this.title5 = 'a man wearing a lei seated in a room full of people';
    }

    if (this.surveyName == '922') {
        this.title4 = '一位老人正在露天市场检查一些香蕉';
        this.title5 = 'an older man is inspecting some bananas at an outdoor market.';
    }

    if (this.surveyName == '923') {
        this.title4 = '一位女士坐在桌边，旁边有人正在往酒杯里倒一瓶酒';
        this.title5 = 'a woman is sitting at a table with a person pouring a bottle of wine into a wine glass.';
    }

    if (this.surveyName == '924') {
        this.title4 = '一男一女坐在木桌旁，桌上摆着香槟和蛋糕';
        this.title5 = 'a man and woman sitting by a wooden table with champagne and cake.';
    }

    if (this.surveyName == '925') {
        this.title4 = '三个人坐在一张圆形木桌旁，享用食物';
        this.title5 = 'three people seated at a round wooden table with food.';
    }

    if (this.surveyName == '926') {
        this.title4 = '一名男子坐在摆满食物的桌子前，用玻璃杯喝酒';
        this.title5 = 'a man drinking from a glass while sitting in front of a table full of food';
    }

    if (this.surveyName == '927') {
        this.title4 = '一名男子站在镜子前调整领带';
        this.title5 = 'a man standing in front of a mirror adjusting his tie';
    }

    if (this.surveyName == '928') {
        this.title4 = '两个男人站在带有木质橱柜的厨房里';
        this.title5 = 'two men stand in a kitchen with wooden cabinets.';
    }

    if (this.surveyName == '929') {
        this.title4 = '一个穿制服的男孩背对篮筐，手里拿着一个篮球';
        this.title5 = 'A uniformed boy is holding a basketball with his back to the hoop.';
    }

    if (this.surveyName == '930') {
        this.title4 = '一辆海滩巡逻吉普车沿着沙滩行驶，人们在浅水中嬉戏并放风筝';
        this.title5 = 'A beach patrol jeep drives along the sand as people wade and fly kites down the beach.';
    }

    if (this.surveyName == '931') {
        this.title4 = '一个小女孩拿着录音器站在桌子旁，桌上有未完成的画作';
        this.title5 = 'Small girl holding a recorder near a table with an unfinished drawing.';
    }

    if (this.surveyName == '932') {
        this.title4 = '一只猫站在电视机前的桌子上';
        this.title5 = 'A cat standing on a table in front of a TV.';
    }

    if (this.surveyName == '933') {
        this.title4 = '一只猫坐在毯子上，毯子旁边有一台笔记本电脑';
        this.title5 = 'A cat sitting on a blanket next to a laptop.';
    }

    if (this.surveyName == '934') {
        this.title4 = '一个小男孩站在一堆行李箱旁边';
        this.title5 = 'A little boy stands by a stack of suitcases.';
    }

    if (this.surveyName == '935') {
        this.title4 = '两只长颈鹿站在动物园的围栏后面';
        this.title5 = 'Two giraffes stand behind a fence in a zoo enclosure.';
    }

    if (this.surveyName == '936') {
        this.title4 = '两个图像：一个女人和一只猫在窗前，另一个是有施工标志的高速公路';
        this.title5 = 'Two images: A woman and a cat at a window and a highway with a construction sign.';
    }

    if (this.surveyName == '937') {
        this.title4 = '这三只鸟在海滩上寻找食物';
        this.title5 = 'These three birds are walking along the beach looking for food.';
    }

    if (this.surveyName == '938') {
        this.title4 = '一头大象在泥路上跟在一辆车后面走';
        this.title5 = 'A large elephant walking behind a car on a dirt road.';
    }

    if (this.surveyName == '939') {
        this.title4 = '一个小女孩坐在人行道上用手机';
        this.title5 = 'A young girl sitting on the sidewalk using her phone.';
    }

    if (this.surveyName == '940') {
        this.title4 = '一个年轻男子拿着网球拍站在球场上';
        this.title5 = 'A young man holding a tennis racquet on a court.';
    }

    if (this.surveyName == '941') {
        this.title4 = '一个田野里，背景有三头牛在吃草';
        this.title5 = 'A field with three cows grazing in the background.';
    }

    if (this.surveyName == '942') {
        this.title4 = '几个人在机场看着一堆行李';
        this.title5 = 'Multiple men looking at a stack of luggage at an airport.';
    }

    if (this.surveyName == '943') {
        this.title4 = '两匹马在围栏的草地上奔跑';
        this.title5 = 'Two horses running inside a grassy fenced pasture.';
    }

    if (this.surveyName == '944') {
        this.title4 = '一群人牵着马穿过田野';
        this.title5 = 'A group of people walking horses through a field.';
    }

    if (this.surveyName == '945') {
        this.title4 = '几只鸟在日落时分走过海岸';
        this.title5 = 'Several birds walk across the shore as the sun sets.';
    }

    if (this.surveyName == '946') {
        this.title4 = '两个孩子坐着，其中一个看起来有些痛苦';
        this.title5 = 'Two children are sitting and one appears to be in pain.';
    }

    if (this.surveyName == '947') {
        this.title4 = '两个微笑的男人在厨房里准备热狗面包';
        this.title5 = 'Two smiling men prepare hot dog rolls in a kitchen.';
    }

    if (this.surveyName == '948') {
        this.title4 = '一位年轻人坐在一头大象上面。';
        this.title5 = 'a young man who is sitting on top of very large elephant.';
    }

    if (this.surveyName == '949') {
        this.title4 = '一名女子正准备用球拍击打网球，一群观众在看台上观看。';
        this.title5 = 'a woman getting ready to hit a tennis ball with a racket while a group of people watch from the stands.';
    }

    if (this.surveyName == '950') {
        this.title4 = '一大群鸟站在停车场靠近汽车的空地上。';
        this.title5 = 'a large group of birds are standing in the parking lot in empty spaces near cars.';
    }

    if (this.surveyName == '951') {
        this.title4 = '一名妇女和带着行李的孩子们的铜像';
        this.title5 = 'a bronze statue of a woman and children with luggage';
    }

    if (this.surveyName == '952') {
        this.title4 = '一只灰白相间的猫靠在一台小电视上。';
        this.title5 = 'a gray and white cat leaning up against a small tv.';
    }

    if (this.surveyName == '953') {
        this.title4 = '一个女孩休息时捂着额头，握着网球拍。';
        this.title5 = 'a girl holding her brow and her tennis racquet as she takes a break.';
    }

    if (this.surveyName == '954') {
        this.title4 = '坐在城市建筑上打电话的年轻女性的修改照片';
        this.title5 = 'altered photograph of young women on cell phones sitting on city buildings';
    }

    if (this.surveyName == '955') {
        this.title4 = '一辆救世军的卡车，人们站在车周围，从卡车上取饮料。';
        this.title5 = 'a salvation army truck with people standing around the vehicle and getting beverages from the truck.';
    }

    if (this.surveyName == '956') {
        this.title4 = '一座大钟悬挂在两个人行走的平台上方。';
        this.title5 = 'a big clock hangs above a platform that two people are walking on.';
    }

    if (this.surveyName == '957') {
        this.title4 = '一张模糊的照片，人们在雨中撑着伞走在街上。';
        this.title5 = 'a blurry picture of people walking on the street in the rain with their umbrellas.';
    }

    if (this.surveyName == '958') {
        this.title4 = '一群人站在那里看着一个女人切一块蛋糕，桌子上摆着一堆咖啡杯和碟子。';
        this.title5 = 'a bunch of people are standing around watching a woman cut a piece of cake and there are a bunch of coffee cups and saucers on the table.';
    }

    if (this.surveyName == '959') {
        this.title4 = '一个正在航行的蜂蜜烤坚果的男人和在雨中撑着伞走在街上的人们';
        this.title5 = 'a man that is sailing honey-roasted nuts and people walking down the street with their umbrellas in the rain';
    }

    if (this.surveyName == '960') {
        this.title4 = '一名男子撑开一把彩色雨伞，另一人则将脚伸进雨伞。';
        this.title5 = 'a man is holding a colorful umbrella open while another person sticks his feet into it.';
    }

    if (this.surveyName == '961') {
        this.title4 = '一位老年男子和一位老年妇女坐在繁忙的人行道上的长椅上。';
        this.title5 = 'an elderly man and woman sit on a bench on a busy sidewalk.';
    }

    if (this.surveyName == '962') {
        this.title4 = '繁忙的街道两旁，人潮涌动。';
        this.title5 = 'a large number of people walk along all sides of a busy street.';
    }

    if (this.surveyName == '963') {
        this.title4 = '一只猫坐在停在窗户下的踏板车上';
        this.title5 = 'A cat sitting on a scooter parked below a window.';
    }

    if (this.surveyName == '964') {
        this.title4 = '一只孤独的狗被拴在一个长凳旁边';
        this.title5 = 'A lonely leashed dog sits beside a bench.';
    }

    if (this.surveyName == '965') {
        this.title4 = '两个女士坐着喝饮料，一个在抽烟，另一个坐在冰箱里';
        this.title5 = 'Two ladies seated with cups of drinks, one smoking while the other seated inside a fridge.';
    }

    if (this.surveyName == '966') {
        this.title4 = '两只长颈鹿从两个不同的房间彼此对视';
        this.title5 = 'Two giraffes looking at each other from two separate rooms.';
    }

    if (this.surveyName == '967') {
        this.title4 = '一个女人带着滑雪板和两只棕色的狗站在雪地里，看着镜头';
        this.title5 = 'A woman with skis and two tan dogs standing in the snow looking at the camera.';
    }

    if (this.surveyName == '968') {
        this.title4 = '两只鸟站在长颈鹿的背上';
        this.title5 = 'Two birds sitting on the back of a giraffe.';
    }

    if (this.surveyName == '969') {
        this.title4 = '雪天，一只猫坐在自行车座上，低头看着另一只猫';
        this.title5 = 'a cat sits on the seat of a bicycle and looks down at another cat on a snowy day';
    }

    if (this.surveyName == '970') {
        this.title4 = '一只猫坐在长凳上，旁边是一个乌龟形状的花盆';
        this.title5 = 'a cat sits on a bench near a flower pot shaped like a turtle.';
    }

    if (this.surveyName == '971') {
        this.title4 = '身穿黑色皮夹克和黑色牛仔裤的步行男子。全长工作室拍摄，白色背景';
        this.title5 = 'Walking man in black leather jacket and black jeans. Full length studio shot isolated on white.';
    }

    if (this.surveyName == '972') {
        this.title4 = '圣诞树前的喜庆情侣';
        this.title5 = 'Festive couple in front of Christmas tree.';
    }

    if (this.surveyName == '973') {
        this.title4 = '婴儿礼物太可爱了，在礼物上系一条丝带，再用衣夹夹上一些袜子，瞧';
        this.title5 = 'So Cute For Baby Gifts Tie A Ribbon Around The Gift And Attach Some Socks With Clothespins Voila.';
    }

    if (this.surveyName == '974') {
        this.title4 = '烧烤烤架上土豆的高角度视图';
        this.title5 = 'High Angle View Of Potatoes On Barbecue Grill.';
    }

    if (this.surveyName == '975') {
        this.title4 = '1991 年环法自行车赛期间，威尔弗里德·彼得斯推搡了一位身着世界冠军队服的调皮年轻粉丝';
        this.title5 = "Wilfried Peeters pushes a cheeky young fan dressed in the World Champion's colours, during the 1991 Tour de France.";
    }

    if (this.surveyName == '976') {
        this.title4 = '身穿红色衣服的小丑木偶操纵者。平面卡通风格';
        this.title5 = 'Puppeteer with clown puppet in red clothes. Raster illustration in flat cartoon style.';
    }

    if (this.surveyName == '977') {
        this.title4 = '伊拉·格拉斯微笑着望向远方的照片，他面前放着一个麦克风';
        this.title5 = 'A photo of Ira Glass smiling and looking into the distance. There is a microphone in front of him.';
    }

    if (this.surveyName == '978') {
        this.title4 = '黑森林蛋糕和鲜花';
        this.title5 = 'Black forest cake and flowers.';
    }

    if (this.surveyName == '979') {
        this.title4 = '快乐的两代人活跃的家庭在客厅里跳舞，无忧无虑的老祖父和可爱的学龄前孙子在家里一起听音乐、跳跃、享受时光';
        this.title5 = 'Happy two age generations active family dancing in living room, carefree old senior adult grandfather and cute preschool grandson having fun listening to music jumping enjoying time together at home.';
    }

    if (this.surveyName == '980') {
        this.title4 = '女运动员奔跑和跳跃。红色背景下健康的非洲女性锻炼的侧视图';
        this.title5 = 'Female athlete running and jumping. Side view shot of healthy african woman working out against red background.';
    }

    if (this.surveyName == '981') {
        this.title4 = '有小提琴的美丽的女孩，背景是白色';
        this.title5 = 'Beautiful young girl with violin, isolated on white.';
    }

    if (this.surveyName == '982') {
        this.title4 = '指着头上带着悲伤的生态纸袋的男人，全身';
        this.title5 = 'pointing man with sad ecological paper bag on head, full length.';
    }

    if (this.surveyName == '983') {
        this.title4 = '2017 年温彻斯特大教堂合唱团';
        this.title5 = 'Choir at Winchester Cathedral in 2017';
    }

    if (this.surveyName == '984') {
        this.title4 = '缅甸伊洛瓦底江畔沐浴的妇女';
        this.title5 = 'Women taking bath on irrawaddy river banks, Myanmar';
    }

    if (this.surveyName == '985') {
        this.title4 = '年轻的黑发商业女性在电脑后面有颈部疼痛';
        this.title5 = 'Young brunette business woman has pain neck behind computer — #20863283';
    }

    if (this.surveyName == '986') {
        this.title4 = '大绝妙普拉提动作';
        this.title5 = 'Top 5 Kickass Pilates Moves';
    }

    if (this.surveyName == '987') {
        this.title4 = '科瓦尔斯基正在思考国王朱利安';
        this.title5 = 'Kowalski is thinking about King Julien.';
    }

    if (this.surveyName == '988') {
        this.title4 = '购买库存照片——白天在户外坐在地上，无忧无虑地通过耳机听音乐的年轻女子';
        this.title5 = 'Buy stock photo Shot of a carefree young woman seated on the floor while listening to music through her earphones outside during the day.';
    }

    if (this.surveyName == '989') {
        this.title4 = '浅黄色上衣和裙子';
        this.title5 = 'Top and skirt Light yellow.';
    }

    if (this.surveyName == '990') {
        this.title4 = '女人在家用刷子刷墙，照片';
        this.title5 = 'woman paints wall with brush at home photo.';
    }

    if (this.surveyName == '991') {
        this.title4 = '哈兰县妇女主席 Lindsey Boggs 和青年农民主席 Nathan Boggs 为五年级学生授课';
        this.title5 = "Harlan County Women's Chair Lindsey Boggs and Young Farmer Chair Nathan Boggs teach 5th graders";
    }

    if (this.surveyName == '992') {
        this.title4 = '年轻的陶工在陶轮上旋转粘土';
        this.title5 = "Young potter spinning clay on the potter's wheel.";
    }

    if (this.surveyName == '993') {
        this.title4 = '女孩刷牙';
        this.title5 = 'girl brushing her teeth';
    }

    if (this.surveyName == '994') {
        this.title4 = '安娜·帕奎因与她的狗的照片';
        this.title5 = 'Photos of Anna Paquin With her Dogs';
    }

    if (this.surveyName == '995') {
        this.title4 = '侧平板支撑带手臂伸展';
        this.title5 = 'Side Plank with Arm Extension';
    }

    if (this.surveyName == '996') {
        this.title4 = '年轻夫妇驾驶汽车';
        this.title5 = 'Young couple driving car';
    }

    if (this.surveyName == '997') {
        this.title4 = '生活方式-犹他州凯恩维尔，日落时分，梅丽莎·哈格多恩在亨利山下练习瑜伽';
        this.title5 = 'Lifestyle-Melissa Hagedorn doing yoga below the Henry Mountains at sunset, Cainville, Utah';
    }

    if (this.surveyName == '998') {
        this.title4 = '在苏格兰边境的一家农场，萨福克杂交母羊正在接受羊羔扫描，以预测即将到来的产羔季节的出生率。';
        this.title5 = 'Falside, Chesters, Hawick, Scotland, UK. 17th January 2016. Suffolk cross ewes are scanned for lambs in order to forecast the birth rate for the forthcoming lambing season at a farm in the Scottish Borders.';
    }

    if (this.surveyName == '999') {
        this.title4 = '欧洲赛艇锦标赛';
        this.title5 = 'ROW-The European Rowing Championships Royalty Freegraphy';
    }

    if (this.surveyName == '1000') {
        this.title4 = '越野摩托车在赛道上比赛';
        this.title5 = 'Motocross bikes racing in track —';
    }

    if (this.surveyName == '1001') {
        this.title4 = '儿童艺术 - 查尔斯·伯顿·巴伯 (Charles Burton Barber) 创作的《女孩与狗》';
        this.title5 = 'Kid Art -Girl with Dogs by Charles Burton Barber';
    }

    if (this.surveyName == '1002') {
        this.title4 = '震惊与敬畏-一位新娘在婚礼上看到亚当莱文站在台上时惊呆了';
        this.title5 = "Shock and awe-A bride's jaw dropped upon seeing Adam Levine on stage at her wedding";
    }

    if (this.surveyName == '1003') {
        this.title4 = '她可爱极了-由内布拉斯加州奥马哈的 Lacy Marie 摄影';
        this.title5 = '{She as cute as can be.} by Lacy Marie Photography in Omaha NE';
    }

    if (this.surveyName == '1004') {
        this.title4 = '一群火烈鸟（红鹳）在地上进食';
        this.title5 = 'Group of flamingos (phoenicopterus) eating on the ground';
    }

    if (this.surveyName == '1005') {
        this.title4 = '狮子如何重返世界上最严酷的沙漠之一';
        this.title5 = 'How lions returned to one of the harshest deserts in the world';
    }

    if (this.surveyName == '1006') {
        this.title4 = '布罗克顿小学举行了品德教育大会。十六名学生被选为本月学生，代表幼儿园至五年级的学生。这些学生每天都表现出责任感的性格特征';
        this.title5 = 'Brocton Elementary School celebrated its character education assembly. Sixteen students were chosen as Students of the Month representing grades Kindergarten through 5.';
    }

    if (this.surveyName == '1007') {
        this.title4 = '活跃的运动型狗狗在海边奔跑';
        this.title5 = 'Active athletic dog puppy running at the sea.';
    }

    if (this.surveyName == '1008') {
        this.title4 = '一大群穿红、白、蓝色衣服的员工举起双手庆祝';
        this.title5 = 'Big group of employees in red, white, and blue raise their arms in celebration.';
    }

    if (this.surveyName == '1009') {
        this.title4 = '肯尼亚马赛马拉国家保护区的非洲象';
        this.title5 = 'African Elephant in the Maasai Mara National Reserve, Kenya, Africa.';
    }

    if (this.surveyName == '1010') {
        this.title4 = '发送信息以保护灰狼！';
        this.title5 = 'Send a message to protect the gray wolf!';
    }

    if (this.surveyName == '1011') {
        this.title4 = '青春的照片——快乐的年轻漂亮女子与复古自行车';
        this.title5 = 'foto of adolescence -Happy young beautiful woman with retro bicycle.';
    }

    if (this.surveyName == '1012') {
        this.title4 = '刺绣蕾丝雪纺衬衫，女士红色衬衫';
        this.title5 = 'Embroidery Lace Chiffon Blouse Red Shirt Women.';
    }

    if (this.surveyName == '1013') {
        this.title4 = '一位漂亮的穿白色T恤的女孩微笑着摆出灵感手势，眼睛看向远方，灰色背景下的全景照片';
        this.title5 = 'Photo for happy beautiful girl in white t-shirt showing idea gesture and looking away isolated on grey, panoramic shot.';
    }

    if (this.surveyName == '1014') {
        this.title4 = '无论 2012 年大选结果如何，约瑟夫表示印度都羡慕美国的两党制。数十个政党的数百名候选人参加竞选，他们的支持者骑着摩托车穿梭于各个城市游说选民';
        this.title5 = 'two-party system with envy. Hundreds of candidates from dozens of parties campaign, and their supporters ride through cities on motorbikes to lobby voters.';
    }

    if (this.surveyName == '1015') {
        this.title4 = '后台 Calvin Klein Collection 2015 秋冬男装';
        this.title5 = 'Backstage Calvin Klein Collection Fall Winter 2015 Menswear';
    }

    if (this.surveyName == '1016') {
        this.title4 = '一只野生雄性蓝眼蜻蜓栖息在美国加利福尼亚州因约主教县附近让·勒布朗路旁的一条运河旁的灌木丛上';
        this.title5 = 'a wild male blue-eyed darner rhionaeschna multicolor perches on a bush along a canal off jean leblanc road near bishop inyo county california united states';
    }

    if (this.surveyName == '1017') {
        this.title4 = '三趾树懒在抓挠，巴拿马 - 树懒';
        this.title5 = 'Three-toed Sloth scratching, Panama -Sloth';
    }

    if (this.surveyName == '1018') {
        this.title4 = '竞争激烈-性别专家认为，2004 年电影《贱女孩》中展现的女性行为刻薄，是因为缺少好男人';
        this.title5 = 'Competitive-The catty female behaviour, as displayed in 2004 film Mean Girls, is down to the lack of good men, a gender expert argues';
    }

    if (this.surveyName == '1019') {
        this.title4 = 'BeX 与她的乐队在贝德福德现场表演';
        this.title5 = 'BeX live with her Band at The Bedford';
    }

    if (this.surveyName == '1020') {
        this.title4 = '獒犬/大丹犬混种狗在弗吉尼亚州弗雷德里克斯堡等待领养 - 斯波特瑟尔韦尼亚收容所';
        this.title5 = 'Mastiff/Great Dane Mix Dog for adoption in Fredericksburg, Virginia -Spotsylvania Shelter.';
    }

    if (this.surveyName == '1021') {
        this.title4 = '丛林健身器-小男孩在公园玩耍';
        this.title5 = 'jungle gym-Young boy playing at a park';
    }

    if (this.surveyName == '1022') {
        this.title4 = '森林大象与水牛';
        this.title5 = 'Forest Elephant and Buffalo';
    }

    if (this.surveyName == '1023') {
        this.title4 = '波士顿红袜队 11 号球员拉斐尔·德弗斯在马萨诸塞州波士顿芬威球场对阵纽约洋基队的比赛第二局中击出两分全垒打后与队友击掌庆祝';
        this.title5 = 'BOSTON, MA -AUGUST 18-Rafael Devers #11 of the Boston Red Sox high fives teammates after hitting a two run home run during the second inning of a game against the New York Yankees.';
    }

    if (this.surveyName == '1024') {
        this.title4 = '一位女士面带笑容，望向远方。她长长的棕色头发在她身旁飘扬，身穿白色背心，肩带很粗。只能看到她的肩膀和脸';
        this.title5 = 'A woman looks off into the distance with a big smile on her face. Her long brown hair flows around her, and she wears a white tank top with thick straps. Only her shoulders and face are visible.';
    }

    if (this.surveyName == '1025') {
        this.title4 = '一个女孩和她的马，日落海滩，瓦胡岛北岸';
        this.title5 = 'A Girl and Her Horse, Sunset Beach, North Shore of Oahu';
    }

    if (this.surveyName == '1026') {
        this.title4 = '幼年大角猫头鹰';
        this.title5 = 'Juvenile Great Horned Owl';
    }

    if (this.surveyName == '1027') {
        this.title4 = '一位居民跑过台风海马袭击后倒塌的加油站屋顶，地点是圣尼古拉斯';
        this.title5 = 'A resident runs past a collapsed roof of a petrol station after Typhoon Haima struck San Nicolas.';
    }

    if (this.surveyName == '1028') {
        this.title4 = '海滩上的日落瑜伽';
        this.title5 = 'sunset yoga on the beach.';
    }

    if (this.surveyName == '1029') {
        this.title4 = '混血老年人与年轻家庭成员在厨房里边做圣诞晚餐边聊天的特写，库存照片';
        this.title5 = 'Mixed race senior and young adult family members talking in the kitchen while preparing Christmas dinner together, close up Stockfoto.';
    }

    if (this.surveyName == '1030') {
        this.title4 = 'Rosanio 摄影 | 新罕布什尔州本森公园孕妇摄影师';
        this.title5 = 'Rosanio Photography | Benson Park, New Hampshire Maternity Photographer.';
    }

    if (this.surveyName == '1031') {
        this.title4 = '华盛顿州克拉克斯顿燕子公园的一群美洲反嘴鹬';
        this.title5 = 'Flock of American Avocets at Swallows Park in Clarkston, Washington.';
    }

    if (this.surveyName == '1032') {
        this.title4 = '年轻漂亮的女孩骑着自行车，背景中有两个男人';
        this.title5 = 'Young beautiful girl on a bicycle with two men in the background Royalty Free Stock Image';
    }

    if (this.surveyName == '1033') {
        this.title4 = '日间护理手指画烹饪';
        this.title5 = 'DAY CARE FINGER PAINT COOKING 021';
    }

    if (this.surveyName == '1034') {
        this.title4 = '学生拉小提琴';
        this.title5 = 'A student plays violin';
    }

    if (this.surveyName == '1035') {
        this.title4 = '吉尔吉斯斯坦首支女子曲棍球队的支持者在吉尔吉斯斯坦奥特拉德诺耶村的一场训练比赛中欢呼加油';
        this.title5 = "Supporters of Kyrgyzstan's first female hockey team cheer during a training match in the village of Otradnoye, Kyrgyzstan.";
    }

    if (this.surveyName == '1036') {
        this.title4 = '德国普通秃鹰飞行';
        this.title5 = 'Kimball Stock Common Buzzard Flying In Germany';
    }

    if (this.surveyName == '1037') {
        this.title4 = 'Ryan 假装玩耍并用巨型蜡笔蛋惊喜玩具学习颜色';
        this.title5 = 'Ryan Pretend Play and Learn Colors with Giant Crayons Egg Surprise Toys!';
    }

    if (this.surveyName == '1038') {
        this.title4 = '快乐儿童与气球';
        this.title5 = 'Happy children with balloons — Stock fotografie';
    }

    if (this.surveyName == '1039') {
        this.title4 = '女孩喝汤';
        this.title5 = 'Girl eating soup —';
    }

    if (this.surveyName == '1040') {
        this.title4 = '节日狗狗芭蕾舞裙-绿色和红色';
        this.title5 = 'Holiday Dog Tutu  -Green and Red';
    }
  },
//   mounted() {
//     this.image = '(' + this.surveyName + ')'
//     const imagesToPreload = ['image1.jpg', 'image2.jpg', 'image3.jpg']; // 图片列表
//     imagesToPreload.forEach(imageUrl => {
//       const img = new Image();
//       img.src = imageUrl;
//     });
//   }
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
.el-radio-group /deep/ .el-radio__inner{
  display: none !important;
}

/* .el-radio /deep/ .el-radio__label{
    font-size:20px !important;
  } */

/* .el-carousel__item:nth-child(2n) {
   background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
   background-color: #d3dce6;
} */
</style>