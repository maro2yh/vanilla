<template>
  <div class="flex flex-wrap mt-4">
    <div class="w-full lg:w-4/12 xl:w-12/12 px-4">
      <normal-table
        title="테마"
        :headers="table.headers"
        :rowsData="table.data"
        :options="table.options"
        @onClickRow="onClickThemeRow"
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
import NormalTable from "@/components/Tables/NormalTable.vue"

export default {
  name: "themestatus-page",
  components: {
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
      selectionTheme: null,
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
      "text": "테마",
      "field": "name"
    });
    this.table.headers.push({
      "text": "등락율",
      "field": "changeRate"
    });

    this.getThemeList();

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
    async getThemeList() {
      this.axios.get("/theme").then(response => {
        this.table.data = response.data;

        if (!this.selectionTheme) {
          this.selectionTheme = this.table.data[0];
          this.getThemeItemList(this.selectionTheme.no);
        }
      }).catch((error) => {
        console.log(error);
      });
    },
    async getThemeItemList(no) {
      this.axios.get("/theme/" + no).then(response => {
        this.itemTable.data = response.data;
      }).catch((error) => {
        console.log(error);
      });
    },
    onClickThemeRow (rowData) {
      if (this.selectionTheme.no === rowData.data.no) {
        return;
      }

      this.selectionTheme = rowData.data;
      this.getThemeItemList(this.selectionTheme.no);
    }
  }
}
</script>
