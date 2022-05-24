<template>
  <div>
    <div id="map" style="width: 100%; height: 30vh"></div>
    <div class="p-2" style="width: 100%; height: 10vh; background-color: gray">
      <div class="d-flex">
        <i class="fa fa-search"></i>
        <h6 class="ps-2">검색 방법을 선택하세요</h6>
      </div>
      <div class="py-1 px-2 d-flex">
        <div class="form-check pe-3">
          <input
            value="D"
            v-model="searchType"
            class="form-check-input"
            type="radio"
            id="searchByDong"
          />
          <label class="form-check-label" for="searchByDong">동 검색</label>
        </div>
        <div class="form-check">
          <input
            value="K"
            v-model="searchType"
            class="form-check-input"
            type="radio"
            id="searchByKeyword"
          />
          <label class="form-check-label" for="searchByKeyword" /> 키워드 검색
        </div>
      </div>
    </div>
    <div v-if="searchType == 'D'" class="pb-2 d-flex justify-content-evenly">
      <div class="btn-group">
        <button type="button" class="btn btn-primary">서울시</button>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-6">
        <fieldset>
          <select
            v-model="selectGuName"
            @change="onGuMenuChange"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="default" selected>구 선택</option>
            <option
              v-for="(item, index) in gu"
              :key="index"
              :value="item.gugunCode"
            >
              {{ item.gugunName }}
            </option>
          </select>
        </fieldset>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-6">
        <fieldset>
          <select
            v-model="selectDongName"
            @change="onDongMenuChange"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="default" selected>동 선택</option>
            <option
              v-for="(item, index) in dong"
              :key="index"
              :value="item.dongCode"
            >
              {{ item.dongName }}
            </option>
          </select>
        </fieldset>
      </div>
    </div>
    <div v-if="searchType == 'K'" class="input-group pb-2 px-3">
      <input
        @keyup.enter="onKeywordSearch"
        type="text"
        v-model="keyword"
        class="form-control d-inline-block"
        placeholder="건물명 또는 동을 입력하세요"
      />
      <button
        @click="onKeywordSearch"
        class="btn btn-primary d-inline-block"
        type="button"
      >
        <i class="bi bi-search"></i>
      </button>
    </div>
    <div v-if="visible" id="showList" class="card p-0 bg-secondary">
      <div class="bg-white mb-2">
        <div class="px-3">
          <div>
            <h4 class="m-0">{{ apt[curIndex].aptName }}</h4>
          </div>
          <div class="border-bottom d-flex py-2">
            <div class="text-secondary w-25">주소</div>
            <div>{{ apt[curIndex].dongName }} {{ apt[curIndex].jibun }}</div>
          </div>
          <div class="d-flex py-2">
            <div class="text-secondary w-25">건축년도</div>
            <div>{{ apt[curIndex].buildYear }}</div>
          </div>
        </div>
      </div>
      <div class="bg-white mb-2">
        <div class="border-bottom"><h5 class="p-3 m-0">실거래가</h5></div>
        <div>
          <table class="w-100">
            <thead class="bg-secondary text-white">
              <tr>
                <td class="ps-3 py-1">거래일</td>
                <td>거래가격</td>
                <td>면적</td>
                <td>층수</td>
              </tr>
            </thead>
            <tbody class="px-2">
              <tr
                v-for="(item, index) in dealinfo"
                :key="index"
                class="border-bottom"
              >
                <td class="ps-3 py-2">{{ item.dealYear }}</td>
                <td>{{ item.dealAmount }}</td>
                <td>{{ item.area }}</td>
                <td>{{ item.floor }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import dist from "vuex-persistedstate";
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      searchType: "D",
      selectGuName: "default",
      selectDongName: "default",
      markers: [],
      deals: [],
      map: "",
      curIndex: -1,
      keyword: "",
      visible: false,
    };
  },
  watch: {
    apt: function () {
      this.removeMarkers();
      if (this.visible) this.visible = !this.visible;
      if (this.apt.length) {
        this.addMarkers(this.apt);
        console.log(this.apt);
        this.$swal(`${this.apt.length}건의 데이터가 검색되었다.`, {
          icon: "success",
        });
      } else {
        this.$swal(`${this.selectDongName}에 등록된 건물 정보가 없습니다.`, {
          icon: "error",
        });
      }
    },
  },
  computed: {
    ...mapState(houseStore, [
      "culturalspace",
      "sidos",
      "gu",
      "dong",
      "houses",
      "apt",
      "dealinfo",
    ]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    this.initSearchBox();
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getHouseList",
      "getDong",
      "getApt",
      "getAptDeal",
      "getInfoByKeyword",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
    ]),

    initMap() {
      const mapContainer = document.getElementById("map");
      console.log(mapContainer);
      const mapOption = {
        center: new kakao.maps.LatLng(37.56666, 126.978),
        level: 3,
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      // kakao.maps.event.addListener(this.map, "rightclick", function (mouseEvent) {
      //     if (drawingFlag) {
      //       moveLine.setMap(null);
      //       moveLine = null;
      //       var path = clickLine.getPath();
      //       if (path.length > 1) {
      //         if (dots[dots.length - 1].distance) {
      //           dots[dots.length - 1].distance.setMap(null);
      //           dots[dots.length - 1].distance = null;
      //         }
      //         var distance = Math.round(clickLine.getLength()),
      //           content = getTimeHTML(distance);
      //         showDistance(content, path[path.length - 1]);
      //       } else {
      //         this.deleteClickLine(clickLine);
      //         this.deleteCircleDot();
      //         this.deleteDistance();
      //       }
      //     drawingFlag = false;
      //     }
      //   });
    },
    initSearchBox() {
      this.getGugun(11);
    },
    onGuMenuChange() {
      if (this.selectGuName !== "default") {
        this.selectDongName = "default";
        console.log(this.selectGuName);
        this.getDong(this.selectGuName);
      }
    },
    onDongMenuChange() {
      if (this.selectDongName !== "default") {
        this.eventFrom = "dong";
        this.getApt(this.selectDongName);
      }
    },
    removeMarkers() {
      this.markers.forEach((m) => m.setMap(null));
      this.markers = [];
    },

    addMarkers(list) {
      let bounds = new kakao.maps.LatLngBounds();
      console.log(list);
      list.forEach(({ lat, lng }, index) => {
        console.log(`forEach ${index}`);
        let markerPosition = new kakao.maps.LatLng(lat, lng);
        this.addMarkerByOne(markerPosition, index);
        bounds.extend(markerPosition);
      });
      this.addInfoWindow();
      this.map.setBounds(bounds);
    },
    addMarkerByOne(markerPosition, index) {
      let marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      let $this = this;
      kakao.maps.event.addListener(marker, "click", function () {
        $this.showHouseDetail(index);
      });
      this.markers.push(marker);
      marker.setMap(this.map);
    },
    showHouseDetail(index) {
      this.curIndex = index;
      console.log(this.apt[index].dongName);
      if (!this.visible) this.visible = true;
      const houseNo = this.apt[index].aptCode;
      this.getHouseDeal(houseNo);
      console.log(houseNo);
    },
    getHouseDeal(houseNo) {
      console.log(houseNo);
      this.getAptDeal(houseNo);
    },
    addInfoWindow() {
      console.log("addiw");
      this.markers.forEach((marker, index) => {
        let item = this.apt[index];
        let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`;
        let infoWindow = new kakao.maps.InfoWindow({
          content: infoContents,
        });
        // infoWindow.open(this.map, marker);
        // this.infoWindows.push(infoWindow);
        let $this = this;
        kakao.maps.event.addListener(marker, "mouseover", function () {
          // console.log(1)
          // console.log(this.curInfoWindow)
          // if ($this.curInfoWindow) {
          //   if ($this.curInfoWindow.name != item.aptName) {
          //     $this.curInfoWindow.close();
          //     infoWindow.open(this.map, marker);
          //   }
          // } else {
          //   console.log('ttt')
          infoWindow.open($this.map, marker);
        });
        kakao.maps.event.addListener(marker, "mouseout", function () {
          infoWindow.close();
        });
      });
    },
    onKeywordSearch() {
      if (this.keyword == "") {
        this.$swal("키워드를 입력해주세요.", { icon: "error" });
      } else {
        this.getInfoByKeyword(this.keyword);
      }
    },
    searchApt() {
      this.CLEAR_APT_LIST();
      if (this.dongCode) {
        this.getApt(this.dongCode);
      }
    },
    // deleteClickLine(clickLine) {
    //   if (clickLine) {
    //     clickLine.setMap(null);
    //     clickLine = null;
    //   }
    // },
    // showDistance(content, position) {
    //   if (this.distanceOverlay) {
    //     this.distanceOverlay.setPosition(position);
    //     this.distanceOverlat.setContent(content);
    //   } else {
    //     this.distanceOverlay = new kakao.maps.CustomOverlay({
    //       map: this.map,
    //       content: content,
    //       position: position,
    //       xAnchor: 0,
    //       yAnchor: 0,
    //       zIndex: 3,
    //     });
    //   }
    // },
    // deleteDistance() {
    //   if (this.distanceOverlay) {
    //     this.distanceOverlay.setMap(null);
    //     this.distanceOverlay = null;
    //   }
    // },
    // displayCircleDot(position, distance) {
    //   var circleOverlay = new Kakao.maps.CustomOverlay({
    //     content: '<span class="dot"></span>',
    //     position: position,
    //     zIndex: 1,
    //   });
    //   circleOverlay.setMap(this.map);
    //   if (distance > 0) {
    //     var distanceOverlay = new Kakao.maps.CustomOverlay({
    //       content:
    //       "<div class='dotOverlay'>거리 <span class='number'>" +
    //       distance +
    //       "</span>m</div>",
    //       position: position,
    //       yAnchor: 1,
    //       zIndex: 2,
    //     });
    //     distanceOverlay.setMap(this.map);
    //   }
    //   this.dots.push({circle:circleOverlay, distance:distanceOverlay});
    // },
    // deleteCircleDot() {
    //   var i;
    //   for (i = 0; i < this.dots.length; i++) {
    //     if (this.dots[i].circle) {
    //       this.dots[i].circle.setMap(null);
    //     }
    //     if (this.dots[i].distance) {
    //       this.dots[i].distance.setMap(null);
    //     }
    //   }
    //   this.dots = [];
    // },
    // getTimeHTML(distance) {
    //   var walkTime = (distance / 67) | 0;
    //   var walkHour = "";
    //   var walkMin = "";
    //   if (walkTime > 60) {
    //     walkHour =
    //     "<span class='number'>" +
    //     Math.floor(walkTime / 60) +
    //     "</span>시간 ";
    //   }
    //   walkMin = "<span class='number'>" + (walkTime % 60) + "</span>분";
    //   var bycicleTime = (distance / 227) | 0;
    //   var bycicleHour = "";
    //   var bycicleMin = "";
    //   if (bycicleTime > 60) {
    //     bycicleHour = "<span class='number'>" + Math.floor(bycicleTime / 60) + "</span>시간 ";
    //   }
    //   bycicleMin = "<span class='number'>" + (bycicleTime % 60) + "</span>분";

    //   // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
    //   var content = "<ul class='dotOverlay distanceInfo'>";
    //   content += "    <li>";
    //   content += "        <span class='label'>총거리</span><span class='number'>" + distance + "</span>";
    //   content += "    </li>";
    //   content += "    <li>";
    //   content += "        <span class='label'>도보</span>" + walkHour + walkMin;
    //   content += "    </li>";
    //   content += "    <li>";
    //   content += "        <span class='label'>자전거</span>" + bycicleHour + bycicleMin;
    //   content += "    </li>";
    //   content += "</ul>";

    //   return content;
    // },
  },
};
</script>

<style></style>
