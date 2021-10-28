<template>
  <div class="flex flex-wrap mt-4">
    <div class="w-full lg:w-4/12 xl:w-12/12 px-4">
      <normal-table
        title="업종"
        :headers="table.headers"
        :rowsData="table.data"
        :options="table.options"
        @onClickRow="onClickUpjongRow"
      />
    </div>

    <div class="w-full lg:w-8/12 xl:w-12/12 px-4">
      <normal-table
        title="종목"
        :headers="itemTable.headers"
        :rowsData="itemTable.data"
        :options="itemTable.options"
      />
    </div>
  </div>
</template>
<script>
// import CardTable from "@/components/Cards/CardTable.vue";
import NormalTable from "@/components/Tables/NormalTable.vue"

export default {
  name: "upjongstatus-page",
  components: {
    // CardTable,
    NormalTable
  },
  data() {
    return {
      table: {
        headers: [],
        data: [],
        options: {
          hover: true,
          active: true
        }
      },
      selectionUpjong: null,
      itemTable: {
        headers: [],
        data: [],
        options: {
          hover: true,
          active: false
        }
      }
    }
  },
  beforeMount() {
    this.table.headers.push({
      "text": "업종명",
      "field": "name"
    });
    this.table.headers.push({
      "text": "등락율",
      "field": "changeRate"
    });

    this.getUpjongList();

    this.itemTable.headers.push({
      "text": "종목명",
      "field": "name"
    });
    this.itemTable.headers.push({
      "text": "현재가",
      "field": "price"
    });
    this.itemTable.headers.push({
      "text": "등락율",
      "field": "changeRate"
    });
    this.itemTable.headers.push({
      "text": "거래량",
      "field": "volume"
    });
    this.itemTable.headers.push({
      "text": "이전 거래량",
      "field": "previousVolume"
    });
  },
  methods: {
    async getUpjongList() {
      this.axios.get("/upjong").then(response => {
        this.table.data = response.data;

        if (!this.selectionUpjong) {
          this.selectionUpjong = this.table.data[0];
          this.getUpjongItemList(this.selectionUpjong.no);
        }
      }).catch((error) => {
        console.log(error);
      });
    },
    async getUpjongItemList(no) {
      this.axios.get("/upjong/" + no).then(response => {
        this.itemTable.data = response.data;
      }).catch((error) => {
        console.log(error);
      });
    },
    onClickUpjongRow (rowData) {
      if (this.selectionUpjong.no === rowData.data.no) {
        return;
      }

      this.selectionUpjong = rowData.data;
      this.getUpjongItemList(this.selectionUpjong.no);
    }
  }
}
</script>
