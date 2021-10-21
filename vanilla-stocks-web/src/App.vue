<template>
  <router-view></router-view>
</template>

<script>
export default {
  data() {
    return {
      storageMenus: JSON.parse(window.localStorage.getItem('checkedMenus'))
    }
  },
  beforeMount() {
    if (!this.storageMenus) {
      const checkedMenus = {
        monitorMenus: [],
        managementMenus: [],
        performanceMenus: [],
        qualityMenus: [],
        etcMenus: []
      }

      checkedMenus.monitorMenus.push({ name: 'INMSw', checked: false, path: '/inmsw' });
      checkedMenus.monitorMenus.push({ name: 'INMS', checked: false, path: '/inms' });
      checkedMenus.monitorMenus.push({ name: 'OSP', checked: false, path: '/osp' });
      checkedMenus.monitorMenus.push({ name: 'ITEMS', checked: false, path: '/items' });
      checkedMenus.monitorMenus.push({ name: 'INMD', checked: false, path: '/imnd' });
      checkedMenus.monitorMenus.push({ name: 'VANTS', checked: false, path: '/vants' });
      checkedMenus.monitorMenus.push({ name: 'GTNMS', checked: false, path: '/gtnms' });
      checkedMenus.monitorMenus.push({ name: 'STNMS', checked: false, path: '/stnms' });
      checkedMenus.monitorMenus.push({ name: 'UTNMS', checked: false, path: '/utnms' });
      checkedMenus.monitorMenus.push({ name: 'ANMS', checked: false, path: '/anms' });
      checkedMenus.monitorMenus.push({ name: 'HNMS', checked: false, path: '/hnms' });
      checkedMenus.monitorMenus.push({ name: 'ITMC', checked: false, path: '/itmc' });
      checkedMenus.monitorMenus.push({ name: 'BRNMS', checked: false, path: '/brnms' });
      checkedMenus.monitorMenus.push({ name: 'ITAS', checked: false, path: '/itas' });
      checkedMenus.monitorMenus.push({ name: 'IPNMS', checked: false, path: '/ipnms' });
      checkedMenus.monitorMenus.push({ name: 'ICAS', checked: false, path: '/icas' });
      checkedMenus.monitorMenus.push({ name: 'PEMS', checked: false, path: '/pems' });
      checkedMenus.monitorMenus.push({ name: 'LSNMS', checked: false, path: '/lsnms' });
      checkedMenus.monitorMenus.push({ name: 'ASNMS', checked: false, path: '/asnms' });
      checkedMenus.monitorMenus.push({ name: 'IPSMS', checked: false, path: '/ipsms' });
      checkedMenus.monitorMenus.push({ name: 'LEMS', checked: false, path: '/lems' });
      checkedMenus.monitorMenus.push({ name: 'NPEMS', checked: false, path: '/npems' });

      checkedMenus.managementMenus.push({ name: 'ABAS+', checked: false, path: '/' });
      checkedMenus.managementMenus.push({ name: 'ESS', checked: false, path: '/' });
      checkedMenus.managementMenus.push({ name: 'HDRMS', checked: false, path: '/' });
      checkedMenus.managementMenus.push({ name: 'WHATS', checked: false, path: '/' });
      checkedMenus.managementMenus.push({ name: 'HSRMS', checked: false, path: '/' });
      checkedMenus.managementMenus.push({ name: 'CATS', checked: false, path: '/' });
      checkedMenus.managementMenus.push({ name: 'ABAS++', checked: false, path: '/' });

      checkedMenus.performanceMenus.push({ name: 'PRISM', checked: false, path: '/' });
      checkedMenus.performanceMenus.push({ name: 'NTIS', checked: false, path: '/' });
      checkedMenus.performanceMenus.push({ name: 'VSBS', checked: false, path: '/' });
      checkedMenus.performanceMenus.push({ name: 'CVIP', checked: false, path: '/' });
      checkedMenus.performanceMenus.push({ name: 'IBOSS', checked: false, path: '/' });
      checkedMenus.performanceMenus.push({ name: 'PUBNW', checked: false, path: '/' });
      checkedMenus.performanceMenus.push({ name: 'CAPA', checked: false, path: '/' });

      checkedMenus.qualityMenus.push({ name: 'Q+', checked: false, path: '/' });
      checkedMenus.qualityMenus.push({ name: 'BQMS', checked: false, path: '/' });
      checkedMenus.qualityMenus.push({ name: 'TOS', checked: false, path: '/' });

      checkedMenus.etcMenus.push({ name: 'PERF', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'RGW', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'USER', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'A.O', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'META', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'DMON', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'LCS', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'NAVIS', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'JENINI', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'MDT', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'SNMS1', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'SNMS2', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'SNMS3', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'MNMS', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'MNMST', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'MABAS', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'JIRA', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'ESST', checked: false, path: '/' });
      checkedMenus.etcMenus.push({ name: 'ESSDV', checked: false, path: '/' });

      window.localStorage.setItem('checkedMenus', JSON.stringify(checkedMenus));
    }
  }
}
</script>
