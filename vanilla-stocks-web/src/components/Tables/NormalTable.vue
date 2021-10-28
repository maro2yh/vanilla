<template>
  <div class="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-white">
    <div class="rounded-t mb-0 px-4 py-3 border-0">
      <div class="flex flex-wrap items-center">
        <div class="relative w-full px-4 max-w-full flex-grow flex-1">
          <h3
            class="font-semibold text-lg text-blueGray-700"
          >
            {{ title }}
          </h3>
        </div>
      </div>
    </div>
    <div class="block w-full overflow-x-auto">
      <table class="items-center w-full bg-transparent border-collapse">
        <thead>
          <tr>
            <th class="px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-nowrap font-semibold text-left bg-blueGray-50 text-blueGray-500 border-blueGray-100"
              v-for="(item, index) in headers"
              :key="index"
            >
              {{ item.text }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item1, index1) in rowsData"
            :key="index1"
            :class="[
                options.hover ? 'hover' : '',
                options.active ? (this.activeIndex === index1 ? 'bg-emerald-900 text-white' : '') : ''
              ]"
            @click="onClickRow(item1, index1)"
          >
            <td class="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-nowrap p-4"
              v-for="(item2, index2) in headers"
              :key="index2"
            >
              {{ getFieldText(item1, index2) }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    title: {
      type: String
    },
    headers: {
      type: Array
    },
    rowsData: {
      type: Array
    },
    options: {
      type: Object,
      default: function() {
        return {};
      }
    }
  },
  data() {
    return {
      activeIndex: 0
    }
  },
  watch: {
    rowsData() {
      console.log(this.options.active);
    }
  },
  methods: {
    getFieldText(data, headerIndex) {
      const header = this.headers[headerIndex];
      const field = header.field;
      return data[field];
    },
    onClickRow(data, index) {
      this.activeIndex = index;
      this.$emit("onClickRow", { data: data, index: index });
    }
  }
}
</script>