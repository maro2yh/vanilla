import Badge from '@/components/Badge.vue';
import BaseNav from "@/components/Navbar/BaseNav";
import BaseHeader from '@/components/BaseHeader';
/**
 * You can register global components here and use them as a plugin in your main Vue instance
 */

const GlobalComponents = {
  install(Vue) {
    Vue.component(Badge.name, Badge);
    Vue.component(BaseHeader.name, BaseHeader);
    Vue.component(BaseNav.name, BaseNav);
  }
};

export default GlobalComponents;
