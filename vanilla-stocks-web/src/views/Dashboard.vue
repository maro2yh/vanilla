<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
    </base-header>

    <b-container fluid class="mt--12">
      <b-row style="margin-top: 30px;">
        <b-col xl="12">
          <b-table hover bordered outlined :head-variant="headVariant" :fields="fields" :items="items">
            <template #table-colgroup="scope">
              <col width="60%" />
              <col width="15%" />
              <col width="15%" />
              <col width="10%" />
            </template>
          </b-table>
        </b-col>
      </b-row>
    </b-container>

  </div>
</template>
<script>
  export default {
    components: {
    },
    data() {
      return {
        headVariant: 'dark',
        fields: [
          { key: 'title', label: '제목' },
          { key: 'user', label: '등록자' },
          { key: 'date', label: '등록일' },
          { key: 'view', label: '조회' }
        ],
        items: [
          { title: '공지제목', user: '사용자이름', date: '2021-10-14', view: '1000' },
          { title: '공지제목', user: '사용자이름', date: '2021-10-14', view: '1000' },
          { title: '공지제목', user: '사용자이름', date: '2021-10-14', view: '1000' },
          { title: '공지제목', user: '사용자이름', date: '2021-10-14', view: '1000' }
        ]
      };
    }
  };
</script>
<style>
.table .thead-dark th {
  color: #FFF;
  font-weight: bold;
  font-size: 12px;
}
</style>
