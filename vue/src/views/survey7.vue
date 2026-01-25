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
          Multiple-choice Questionnaire of Semantic Consistency
        </el-col>
        <el-col :span="24" style="color: #808080"><br></el-col>
        <el-col :span="24" style="color: #808080;font-size: 20px;">
          Note: Please choose the most appropriate image.
        </el-col>
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
                  <el-carousel ref="imgs" trigger="click" @change="carouselChange" indicator-position="none" arrow="always" height="600px" :autoplay="false" style="padding-top: 0px;" >
                      <el-carousel-item v-for="(item,index) in question" :key="index">
                          <span style="margin-top:0px;height: 50px;display: inline-block;font-size: 30px;color: #FF8D1A;font-weight: bold;">{{ item.idView }}</span>
                          <br>
                          <!-- <span style="height: 50px;width: 100%;display: inline-block;color: #FF8D1A;font-size: 20px;">{{ item.idViewZ }}</span> -->
                          <span style="font-weight: 600;text-align: center;display:block;font-size: 20px;">{{ title5 }}</span>
                          <!-- <span style="text-align: center;display:block;font-size: 20px;margin-bottom: 10px;">{{ title4 }}</span> -->
                          <span style="text-align: center;display:block;font-size: 20px;margin-bottom: 10px;"><br></span>
                          <el-radio-group v-model="arr[index]" style="margin-left: 45px;" ref="elradiogroup">
                            <el-radio @change="agreeChange(index)" :label="item.select" :key="item.select" v-for="(item,index1) in item.idSelect" style="padding: 0px" ref="elradio">
                                <!-- <img :src="require(`../images/survey7/${index1+1}- ${image}.jpg`)" style="width: 350px;height: 350px;" ref="myImage"/> -->
                                 <img :src="require(`../images/survey7/${index1+1}- ${image}.jpg`)" style="width: 350px;height: 350px;" ref="myImage"/>
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
      <el-dialog title="prompt" :visible.sync="dialogTableVisible2" width="30%">
        <span>Network failure, submission error, or username has already filled out the questionnaire</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible2 = false">cancel</el-button>
          <el-button type="primary" @click="dialogTableVisible2 = false">confirm</el-button>
        </span>
      </el-dialog>
      <el-dialog title="prompt" :visible.sync="dialogTableVisible3" width="30%">
        <span>Network failure, submission error, or username has already filled out the questionnaire</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible3 = false">cancel</el-button>
          <el-button type="primary" @click="dialogTableVisible3 = false">confirm</el-button>
        </span>
      </el-dialog>
      <el-dialog title="prompt" :visible.sync="dialogTableVisible4" width="30%">
        <span>You still have questions ({{nossubmit}}) that have not been submitted, please check</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible4 = false">cancel</el-button>
          <el-button type="primary" @click="dialogTableVisible4 = false">confirm</el-button>
        </span>
      </el-dialog>
      <el-dialog title="prompt" :visible.sync="dialogTableVisible5" width="30%">
        <span>Please fill in your username</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible5 = false">cancel</el-button>
          <el-button type="primary" @click="dialogTableVisible5 = false">confirm</el-button>
        </span>
      </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // title: '1. Fidelity（真实性）',
      title: '1.Semantic Consistency',
      title2: 'Definition：Semantic consistency is the degree to which the image matches the content of the text.',
      title3: '',
      title4: 'A decorative flower vase with lavender in it',
      title5: '',
      image: '',
      loadimage1: '',
      loadimage2: '',
      loadimage3: '',
      loadimage4: '',
      question: [
        {id: 0, idView: "Q: Which of the following images best matches the text description?",idViewZ:"Q: Which of the following images best matches the text description?", idSelect: [{select: '1A',answer: "1.Completely unreal, clearly generated."},{select: '1B',answer: "2.Somewhat unreal, possibly a generated image."},{select: '1C',answer: "3.Neutral."},{select: '1D',answer: "4.Somewhat real, could be a real image."}]},
      ],
      arr: [],
      answer: {
        answerUser: '',
        surveyName: '',
        // answer1: '',
        // answer2: '',
        // answer3: '',
        // answer4: '',
        // answer5: '',
        // answer6: '',
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
      showSubmit: false,
    };
  },
  methods: {
    agreeChange(index){
        if(index == 1) {
          this.$alert('All questions have been completed, please submit', {
            confirmButtonText: 'confirm',
          });
          this.showSubmit = true
        } else {
          this.$refs.imgs.next();
        }
    },
    carouselChange(now) {
      this.percentage = parseInt(now) * 100/1;
      
      if (now == '0') {
        this.title = '1.Semantic Consistency'
        this.title2 = 'Definition：Semantic consistency is the degree to which the image matches the content of the text.'
        this.title3 = ''
        this.image = '(' + this.surveyName + ')'
      } else if(now == '1') {
        this.title = '2. Fidelity'
        this.title2 = 'Definition：Fidelity is the degree of realism in an image.'
        this.title3 = ''
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
      console.log(this.arr)
      for (var i = 0; i < this.arr.length; i++) {
        this.answer['answer' + (i + 1)] = this.arr[i];
      }

      console.log(this.answer)
      this.request.post("/answer/insertAnswer",this.answer).then(res => {
        console.log(this.answer)
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
 
    if (this.surveyName == '801') {
        this.title4 = '';
        this.title5 = 'Man rides on the back of a wooden cart being pulled by two cows.';
    }

    if (this.surveyName == '802') {
        this.title4 = '';
        this.title5 = 'A zoo keeper looking at the back of a caged elephant.';
    }

    if (this.surveyName == '803') {
        this.title4 = '';
        this.title5 = 'A cat sitting amongst books and clothing in a suitcase.';
    }

    if (this.surveyName == '804') {
        this.title4 = '';
        this.title5 = 'A large cat sits on the sofa arm next to a girl using a computer.';
    }

    if (this.surveyName == '805') {
        this.title4 = '';
        this.title5 = 'Three people skateboarding with cement and a large crowd.';
    }

    if (this.surveyName == '806') {
        this.title4 = '';
        this.title5 = 'Four men standing against a brick wall with one of them eating from a food container, one with several carrots stuffed into his mouth, and the other two standing next to them.';
    }

    if (this.surveyName == '807') {
        this.title4 = '';
        this.title5 = 'A big bunch of skiers in a very big snowy field.';
    }

    if (this.surveyName == '808') {
        this.title4 = '';
        this.title5 = 'Two small horses standing in a field covered with grass.';
    }

    if (this.surveyName == '809') {
        this.title4 = '';
        this.title5 = 'Two young woman in a park doing tricks with a frisbee.';
    }

    if (this.surveyName == '810') {
        this.title4 = '';
        this.title5 = 'The young man is sitting in his car with his pet dog and studying from a textbook.';
    }

    if (this.surveyName == '811') {
        this.title4 = '';
        this.title5 = 'Four stuffed teddy bears dressed in pink and posed together.';
    }

    if (this.surveyName == '812') {
        this.title4 = '';
        this.title5 = 'Apples inside a blue container and a cardboard box of bananas.';
    }

    if (this.surveyName == '813') {
        this.title4 = '';
        this.title5 = 'A lighthouse stands on an island in the background as a surfer on an orange board rides the surf while holding a black pole.';
    }

    if (this.surveyName == '814') {
        this.title4 = '';
        this.title5 = 'Two people stand outside of a very old building.';
    }

    if (this.surveyName == '815') {
        this.title4 = '';
        this.title5 = 'a closeup of a computer keyboard and mouse with workers in the background';
    }

    if (this.surveyName == '816') {
        this.title4 = '';
        this.title5 = 'A close up of several people with one holding an umbrella.';
    }

    if (this.surveyName == '817') {
        this.title4 = '';
        this.title5 = 'Two horses standing in an open field on a sunny day.';
    }

    if (this.surveyName == '818') {
        this.title4 = '';
        this.title5 = 'A young boy is making a silly face while staring at a whole pizza.';
    }

    if (this.surveyName == '819') {
        this.title4 = '';
        this.title5 = 'The tennis player is confident about the difficult shot.';
    }

    if (this.surveyName == '820') {
        this.title4 = '';
        this.title5 = 'a cobblestone street has busy sidewalks full of people with umbrellas';
    }

    if (this.surveyName == '821') {
        this.title4 = '';
        this.title5 = 'a man wearing a lei seated in a room full of people';
    }

    if (this.surveyName == '822') {
        this.title4 = '';
        this.title5 = 'an older man is inspecting some bananas at an outdoor market.';
    }

    if (this.surveyName == '823') {
        this.title4 = '';
        this.title5 = 'a woman is sitting at a table with a person pouring a bottle of wine into a wine glass.';
    }

    if (this.surveyName == '824') {
        this.title4 = '';
        this.title5 = 'a man and woman sitting by a wooden table with champagne and cake.';
    }

    if (this.surveyName == '825') {
        this.title4 = '';
        this.title5 = 'three people seated at a round wooden table with food.';
    }

    if (this.surveyName == '826') {
        this.title4 = '';
        this.title5 = 'a man drinking from a glass while sitting in front of a table full of food';
    }

    if (this.surveyName == '827') {
        this.title4 = '';
        this.title5 = 'a man standing in front of a mirror adjusting his tie';
    }

    if (this.surveyName == '828') {
        this.title4 = '';
        this.title5 = 'two men stand in a kitchen with wooden cabinets.';
    }

    if (this.surveyName == '829') {
        this.title4 = '';
        this.title5 = 'A uniformed boy is holding a basketball with his back to the hoop.';
    }

    if (this.surveyName == '830') {
        this.title4 = '';
        this.title5 = 'A beach patrol jeep drives along the sand as people wade and fly kites down the beach.';
    }

    if (this.surveyName == '831') {
        this.title4 = '';
        this.title5 = 'Small girl holding a recorder near a table with an unfinished drawing.';
    }

    if (this.surveyName == '832') {
        this.title4 = '';
        this.title5 = 'A cat standing on a table in front of a TV.';
    }

    if (this.surveyName == '833') {
        this.title4 = '';
        this.title5 = 'A cat sitting on a blanket next to a laptop.';
    }

    if (this.surveyName == '834') {
        this.title4 = '';
        this.title5 = 'A little boy stands by a stack of suitcases.';
    }

    if (this.surveyName == '835') {
        this.title4 = '';
        this.title5 = 'Two giraffes stand behind a fence in a zoo enclosure.';
    }

    if (this.surveyName == '836') {
        this.title4 = '';
        this.title5 = 'Two images: A woman and a cat at a window and a highway with a construction sign.';
    }

    if (this.surveyName == '837') {
        this.title4 = '';
        this.title5 = 'These three birds are walking along the beach looking for food.';
    }

    if (this.surveyName == '838') {
        this.title4 = '';
        this.title5 = 'A large elephant walking behind a car on a dirt road.';
    }

    if (this.surveyName == '839') {
        this.title4 = '';
        this.title5 = 'A young girl sitting on the sidewalk using her phone.';
    }

    if (this.surveyName == '840') {
        this.title4 = '';
        this.title5 = 'A young man holding a tennis racquet on a court.';
    }

    if (this.surveyName == '841') {
        this.title4 = '';
        this.title5 = 'A field with three cows grazing in the background.';
    }

    if (this.surveyName == '842') {
        this.title4 = '';
        this.title5 = 'Multiple men looking at a stack of luggage at an airport.';
    }

    if (this.surveyName == '843') {
        this.title4 = '';
        this.title5 = 'Two horses running inside a grassy fenced pasture.';
    }

    if (this.surveyName == '844') {
        this.title4 = '';
        this.title5 = 'A group of people walking horses through a field.';
    }

    if (this.surveyName == '845') {
        this.title4 = '';
        this.title5 = 'Several birds walk across the shore as the sun sets.';
    }

    if (this.surveyName == '846') {
        this.title4 = '';
        this.title5 = 'Two children are sitting and one appears to be in pain.';
    }

    if (this.surveyName == '847') {
        this.title4 = '';
        this.title5 = 'Two smiling men prepare hot dog rolls in a kitchen.';
    }

    if (this.surveyName == '848') {
        this.title4 = '';
        this.title5 = 'a young man who is sitting on top of very large elephant.';
    }

    if (this.surveyName == '849') {
        this.title4 = '';
        this.title5 = 'a woman getting ready to hit a tennis ball with a racket while a group of people watch from the stands.';
    }

    if (this.surveyName == '850') {
        this.title4 = '';
        this.title5 = 'a large group of birds are standing in the parking lot in empty spaces near cars.';
    }

    if (this.surveyName == '851') {
        this.title4 = '';
        this.title5 = 'a bronze statue of a woman and children with luggage';
    }

    if (this.surveyName == '852') {
        this.title4 = '';
        this.title5 = 'a gray and white cat leaning up against a small tv.';
    }

    if (this.surveyName == '853') {
        this.title4 = '';
        this.title5 = 'a girl holding her brow and her tennis racquet as she takes a break.';
    }

    if (this.surveyName == '854') {
        this.title4 = '';
        this.title5 = 'altered photograph of young women on cell phones sitting on city buildings';
    }

    if (this.surveyName == '855') {
        this.title4 = '';
        this.title5 = 'a salvation army truck with people standing around the vehicle and getting beverages from the truck.';
    }

    if (this.surveyName == '856') {
        this.title4 = '';
        this.title5 = 'a big clock hangs above a platform that two people are walking on.';
    }

    if (this.surveyName == '857') {
        this.title4 = '';
        this.title5 = 'a blurry picture of people walking on the street in the rain with their umbrellas.';
    }

    if (this.surveyName == '858') {
        this.title4 = '';
        this.title5 = 'a bunch of people are standing around watching a woman cut a piece of cake and there are a bunch of coffee cups and saucers on the table.';
    }

    if (this.surveyName == '859') {
        this.title4 = '';
        this.title5 = 'a man that is sailing honey-roasted nuts and people walking down the street with their umbrellas in the rain';
    }

    if (this.surveyName == '860') {
        this.title4 = '';
        this.title5 = 'a man is holding a colorful umbrella open while another person sticks his feet into it.';
    }

    if (this.surveyName == '861') {
        this.title4 = '';
        this.title5 = 'an elderly man and woman sit on a bench on a busy sidewalk.';
    }

    if (this.surveyName == '862') {
        this.title4 = '';
        this.title5 = 'a large number of people walk along all sides of a busy street.';
    }

    if (this.surveyName == '863') {
        this.title4 = '';
        this.title5 = 'A cat sitting on a scooter parked below a window.';
    }

    if (this.surveyName == '864') {
        this.title4 = '';
        this.title5 = 'A lonely leashed dog sits beside a bench.';
    }

    if (this.surveyName == '865') {
        this.title4 = '';
        this.title5 = 'Two ladies seated with cups of drinks, one smoking while the other seated inside a fridge.';
    }

    if (this.surveyName == '866') {
        this.title4 = '';
        this.title5 = 'Two giraffes looking at each other from two separate rooms.';
    }

    if (this.surveyName == '867') {
        this.title4 = '';
        this.title5 = 'A woman with skis and two tan dogs standing in the snow looking at the camera.';
    }

    if (this.surveyName == '868') {
        this.title4 = '';
        this.title5 = 'Two birds sitting on the back of a giraffe.';
    }

    if (this.surveyName == '869') {
        this.title4 = '';
        this.title5 = 'a cat sits on the seat of a bicycle and looks down at another cat on a snowy day';
    }

    if (this.surveyName == '870') {
        this.title4 = '';
        this.title5 = 'a cat sits on a bench near a flower pot shaped like a turtle.';
    }

    if (this.surveyName == '871') {
        this.title4 = '';
        this.title5 = 'Walking man in black leather jacket and black jeans. Full length studio shot isolated on white.';
    }

    if (this.surveyName == '872') {
        this.title4 = '';
        this.title5 = 'Festive couple in front of Christmas tree.';
    }

    if (this.surveyName == '873') {
        this.title4 = '';
        this.title5 = 'So Cute For Baby Gifts Tie A Ribbon Around The Gift And Attach Some Socks With Clothespins Voila.';
    }

    if (this.surveyName == '874') {
        this.title4 = '';
        this.title5 = 'High Angle View Of Potatoes On Barbecue Grill.';
    }

    if (this.surveyName == '875') {
        this.title4 = '';
        this.title5 = "Wilfried Peeters pushes a cheeky young fan dressed in the World Champion's colours, during the 1991 Tour de France.";
    }

    if (this.surveyName == '876') {
        this.title4 = '';
        this.title5 = 'Puppeteer with clown puppet in red clothes. Raster illustration in flat cartoon style.';
    }

    if (this.surveyName == '877') {
        this.title4 = '';
        this.title5 = 'A photo of Ira Glass smiling and looking into the distance. There is a microphone in front of him.';
    }

    if (this.surveyName == '878') {
        this.title4 = '';
        this.title5 = 'Black forest cake and flowers.';
    }

    if (this.surveyName == '879') {
        this.title4 = '';
        this.title5 = 'Happy two age generations active family dancing in living room, carefree old senior adult grandfather and cute preschool grandson having fun listening to music jumping enjoying time together at home.';
    }

    if (this.surveyName == '880') {
        this.title4 = '';
        this.title5 = 'Female athlete running and jumping. Side view shot of healthy african woman working out against red background.';
    }

    if (this.surveyName == '881') {
        this.title4 = '';
        this.title5 = 'Beautiful young girl with violin, isolated on white.';
    }

    if (this.surveyName == '882') {
        this.title4 = '';
        this.title5 = 'pointing man with sad ecological paper bag on head, full length.';
    }

    if (this.surveyName == '883') {
        this.title4 = '';
        this.title5 = 'Choir at Winchester Cathedral in 2017';
    }

    if (this.surveyName == '884') {
        this.title4 = '';
        this.title5 = 'Women taking bath on irrawaddy river banks, Myanmar';
    }

    if (this.surveyName == '885') {
        this.title4 = '';
        this.title5 = 'Young brunette business woman has pain neck behind computer — #20863283';
    }

    if (this.surveyName == '886') {
        this.title4 = '';
        this.title5 = 'Top 5 Kickass Pilates Moves';
    }

    if (this.surveyName == '887') {
        this.title4 = '';
        this.title5 = 'Kowalski is thinking about King Julien.';
    }

    if (this.surveyName == '888') {
        this.title4 = '';
        this.title5 = 'Buy stock photo Shot of a carefree young woman seated on the floor while listening to music through her earphones outside during the day.';
    }

    if (this.surveyName == '889') {
        this.title4 = '';
        this.title5 = 'Top and skirt Light yellow.';
    }

    if (this.surveyName == '890') {
        this.title4 = '';
        this.title5 = 'woman paints wall with brush at home photo.';
    }

    if (this.surveyName == '891') {
        this.title4 = '';
        this.title5 = "Harlan County Women's Chair Lindsey Boggs and Young Farmer Chair Nathan Boggs teach 5th graders";
    }

    if (this.surveyName == '892') {
        this.title4 = '';
        this.title5 = "Young potter spinning clay on the potter's wheel.";
    }

    if (this.surveyName == '893') {
        this.title4 = '';
        this.title5 = 'girl brushing her teeth';
    }

    if (this.surveyName == '894') {
        this.title4 = '';
        this.title5 = 'Photos of Anna Paquin With her Dogs';
    }

    if (this.surveyName == '895') {
        this.title4 = '';
        this.title5 = 'Side Plank with Arm Extension';
    }

    if (this.surveyName == '896') {
        this.title4 = '';
        this.title5 = 'Young couple driving car';
    }

    if (this.surveyName == '897') {
        this.title4 = '';
        this.title5 = 'Lifestyle-Melissa Hagedorn doing yoga below the Henry Mountains at sunset, Cainville, Utah';
    }

    if (this.surveyName == '898') {
        this.title4 = '';
        this.title5 = 'Falside, Chesters, Hawick, Scotland, UK. 17th January 2016. Suffolk cross ewes are scanned for lambs in order to forecast the birth rate for the forthcoming lambing season at a farm in the Scottish Borders.';
    }

    if (this.surveyName == '899') {
        this.title4 = '';
        this.title5 = 'ROW-The European Rowing Championships Royalty Freegraphy';
    }

    if (this.surveyName == '900') {
        this.title4 = '';
        this.title5 = 'Motocross bikes racing in track —';
    }

    if (this.surveyName == '901') {
        this.title4 = '';
        this.title5 = 'Kid Art -Girl with Dogs by Charles Burton Barber';
    }

    if (this.surveyName == '902') {
        this.title4 = '';
        this.title5 = "Shock and awe-A bride's jaw dropped upon seeing Adam Levine on stage at her wedding";
    }

    if (this.surveyName == '903') {
        this.title4 = '';
        this.title5 = '{She as cute as can be.} by Lacy Marie Photography in Omaha NE';
    }

    if (this.surveyName == '904') {
        this.title4 = '';
        this.title5 = 'Group of flamingos (phoenicopterus) eating on the ground';
    }

    if (this.surveyName == '905') {
        this.title4 = '';
        this.title5 = 'How lions returned to one of the harshest deserts in the world';
    }

    if (this.surveyName == '906') {
        this.title4 = '';
        this.title5 = 'Brocton Elementary School celebrated its character education assembly. Sixteen students were chosen as Students of the Month representing grades Kindergarten through 5.';
    }

    if (this.surveyName == '907') {
        this.title4 = '';
        this.title5 = 'Active athletic dog puppy running at the sea.';
    }

    if (this.surveyName == '908') {
        this.title4 = '';
        this.title5 = 'Big group of employees in red, white, and blue raise their arms in celebration.';
    }

    if (this.surveyName == '909') {
        this.title4 = '';
        this.title5 = 'African Elephant in the Maasai Mara National Reserve, Kenya, Africa.';
    }

    if (this.surveyName == '910') {
        this.title4 = '';
        this.title5 = 'Send a message to protect the gray wolf!';
    }

    if (this.surveyName == '911') {
        this.title4 = '';
        this.title5 = 'foto of adolescence -Happy young beautiful woman with retro bicycle.';
    }

    if (this.surveyName == '912') {
        this.title4 = '';
        this.title5 = 'Embroidery Lace Chiffon Blouse Red Shirt Women.';
    }

    if (this.surveyName == '913') {
        this.title4 = '';
        this.title5 = 'Photo for happy beautiful girl in white t-shirt showing idea gesture and looking away isolated on grey, panoramic shot.';
    }

    if (this.surveyName == '914') {
        this.title4 = '';
        this.title5 = 'two-party system with envy. Hundreds of candidates from dozens of parties campaign, and their supporters ride through cities on motorbikes to lobby voters.';
    }

    if (this.surveyName == '915') {
        this.title4 = '';
        this.title5 = 'Backstage Calvin Klein Collection Fall Winter 2015 Menswear';
    }

    if (this.surveyName == '916') {
        this.title4 = '';
        this.title5 = 'a wild male blue-eyed darner rhionaeschna multicolor perches on a bush along a canal off jean leblanc road near bishop inyo county california united states';
    }

    if (this.surveyName == '917') {
        this.title4 = '';
        this.title5 = 'Three-toed Sloth scratching, Panama -Sloth';
    }

    if (this.surveyName == '918') {
        this.title4 = '';
        this.title5 = 'Competitive-The catty female behaviour, as displayed in 2004 film Mean Girls, is down to the lack of good men, a gender expert argues';
    }

    if (this.surveyName == '919') {
        this.title4 = '';
        this.title5 = 'BeX live with her Band at The Bedford';
    }

    if (this.surveyName == '920') {
        this.title4 = '';
        this.title5 = 'Mastiff/Great Dane Mix Dog for adoption in Fredericksburg, Virginia -Spotsylvania Shelter.';
    }

    if (this.surveyName == '921') {
        this.title4 = '';
        this.title5 = 'jungle gym-Young boy playing at a park';
    }

    if (this.surveyName == '922') {
        this.title4 = '';
        this.title5 = 'Forest Elephant and Buffalo';
    }

    if (this.surveyName == '923') {
        this.title4 = '';
        this.title5 = 'BOSTON, MA -AUGUST 18-Rafael Devers #11 of the Boston Red Sox high fives teammates after hitting a two run home run during the second inning of a game against the New York Yankees.';
    }

    if (this.surveyName == '924') {
        this.title4 = '';
        this.title5 = 'A woman looks off into the distance with a big smile on her face. Her long brown hair flows around her, and she wears a white tank top with thick straps. Only her shoulders and face are visible.';
    }

    if (this.surveyName == '925') {
        this.title4 = '';
        this.title5 = 'A Girl and Her Horse, Sunset Beach, North Shore of Oahu';
    }

    if (this.surveyName == '926') {
        this.title4 = '';
        this.title5 = 'Juvenile Great Horned Owl';
    }

    if (this.surveyName == '927') {
        this.title4 = '';
        this.title5 = 'A resident runs past a collapsed roof of a petrol station after Typhoon Haima struck San Nicolas.';
    }

    if (this.surveyName == '928') {
        this.title4 = '';
        this.title5 = 'sunset yoga on the beach.';
    }

    if (this.surveyName == '929') {
        this.title4 = '';
        this.title5 = 'Mixed race senior and young adult family members talking in the kitchen while preparing Christmas dinner together, close up Stockfoto.';
    }

    if (this.surveyName == '930') {
        this.title4 = '';
        this.title5 = 'Rosanio Photography | Benson Park, New Hampshire Maternity Photographer.';
    }

    if (this.surveyName == '931') {
        this.title4 = '';
        this.title5 = 'Flock of American Avocets at Swallows Park in Clarkston, Washington.';
    }

    if (this.surveyName == '932') {
        this.title4 = '';
        this.title5 = 'Young beautiful girl on a bicycle with two men in the background Royalty Free Stock Image';
    }

    if (this.surveyName == '933') {
        this.title4 = '';
        this.title5 = 'DAY CARE FINGER PAINT COOKING 021';
    }

    if (this.surveyName == '934') {
        this.title4 = '';
        this.title5 = 'A student plays violin';
    }

    if (this.surveyName == '935') {
        this.title4 = '';
        this.title5 = "Supporters of Kyrgyzstan's first female hockey team cheer during a training match in the village of Otradnoye, Kyrgyzstan.";
    }

    if (this.surveyName == '936') {
        this.title4 = '';
        this.title5 = 'Kimball Stock Common Buzzard Flying In Germany';
    }

    if (this.surveyName == '937') {
        this.title4 = '';
        this.title5 = 'Ryan Pretend Play and Learn Colors with Giant Crayons Egg Surprise Toys!';
    }

    if (this.surveyName == '938') {
        this.title4 = '';
        this.title5 = 'Happy children with balloons — Stock fotografie';
    }

    if (this.surveyName == '939') {
        this.title4 = '';
        this.title5 = 'Girl eating soup —';
    }

    if (this.surveyName == '940') {
        this.title4 = '';
        this.title5 = 'Holiday Dog Tutu  -Green and Red';
    }


  },

};

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