<template>
  <div>
    <div id="wrapper">
      <div id="map" style="width: 100%; height: 95vh"></div>
      <div id="searchBox" class="card">
        <div class="pb-2 d-flex justify-content-evenly" style="width: 100%">
          <div class="btn-group">
            <button type="button" class="btn btn-primary">서울시</button>
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6">
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
          <div class="col-lg-3 col-md-6 col-sm-6">
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
          <div class="col-lg-4 col-md-6 col-sm-6">
            <fieldset>
              <select
                v-model="selectType"
                @change="onTypeChange"
                class="array-select form-control form-select"
                aria-label="example"
              >
                <option value="default" selected>종류 선택</option>
                <option
                  v-for="(item, index) in type"
                  :key="index"
                  :value="item.typeName"
                >
                  {{ item.typeName }}
                </option>
              </select>
            </fieldset>
          </div>
        </div>
        <div v-if="visible" id="showList" class="card p-0 bg-secondary">
          <div class="bg-white mb-2">
            <div class="px-3">
              <div>
                <h1 class="m-0">{{ types[curIndex].name }}</h1>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">주소</div>
                <div>
                  {{ types[curIndex].sidoname }}
                  {{ types[curIndex].sigunguname }}
                </div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">도로명 주소</div>
                <div>{{ types[curIndex].doroname }}</div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">유형</div>
                <div>{{ types[curIndex].type }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";
const sanggwonStore = "sanggwonStore";
export default {
  name: "SanggwonSearchBar",
  data() {
    return {
      selectGuName: "default",
      selectDongName: "default",
      selectType: "default",
      markers: [],
      deals: [],
      map: "",
      curIndex: -1,
      visible: false,
      type: [
        {
          typeCode: 1,
          typeName: "교육",
        },
        {
          typeCode: 2,
          typeName: "스포츠",
        },
        {
          typeCode: 3,
          typeName: "음식",
        },
      ],
    };
  },
  watch: {
    types: function () {
      this.removeMarkers();
      if (this.types.length) {
        if (this.visible) this.visible = !this.visible;
        this.addMarkers(this.types);
        console.log(this.types);
        this.$swal(`${this.types.length}건의 데이터가 검색되었다.`, {
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
    ...mapState(sanggwonStore, [
      "culturalspace",
      "sidos",
      "gu",
      "dong",
      "houses",
      "apt",
      "dealinfo",
      "types",
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
    ...mapActions(sanggwonStore, [
      "getSido",
      "getGugun",
      "getHouseList",
      "getType",
      "getDong",
      "getApt",
      "getAptDeal",
    ]),
    ...mapMutations(sanggwonStore, [
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
        console.log(this.selectDongName);
      }
    },
    onTypeChange() {
      if (this.selectType !== "default") {
        console.log(this.selectDongName);
        console.log(this.selectType);
        const params = {
          dongCode: this.selectDongName,
          type: this.selectType,
        };
        this.getType(params);
      }
    },
    removeMarkers() {
      this.markers.forEach((m) => m.setMap(null));
      this.markers = [];
    },

    addMarkers(list) {
      console.log(list);
      let bounds = new kakao.maps.LatLngBounds();
      list.forEach(({ lat, lon }, index) => {
        console.log(`forEach ${index}`);
        let markerPosition = new kakao.maps.LatLng(lat, lon);
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
        $this.showDetail(index);
      });
      this.markers.push(marker);
      marker.setMap(this.map);
    },
    showDetail(index) {
      this.curIndex = index;
      if (!this.visible) this.visible = true;
    },
    addInfoWindow() {
      console.log("addiw");
      this.markers.forEach((marker, index) => {
        let item = this.types[index];
        let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${item.name}</div>`;
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
    searchApt() {
      this.CLEAR_APT_LIST();
      if (this.dongCode) {
        this.getApt(this.dongCode);
      }
    },
  },
};
</script>

<style>
#wrapper {
  position: relative;
}
#searchBox {
  position: absolute;
  top: 20px;
  /* bottom : 10px; */
  left: 20px;
  right: 20px;
  width: 500px;
  /* height: 400px; */
  padding: 10px;
  z-index: 100;
  /* background-color:rgba(255, 244, 244, 0.8); */
  /* opacity: 0.5; */
  background-color: rgba(255, 255, 255, 0.7);

  overflow-y: auto;
}
#showList {
  overflow-y: scroll;
  max-height: 60vh;
  z-index: 100;
  background-color: rgba(255, 244, 244, 0.6);
}
</style>
