<template>
	<div class="app" v-if="!appOptions.appEmpty" v-bind:class="{
		'app-header-fixed': appOptions.appHeaderFixed && !appOptions.appHeaderNone,
		'app-sidebar-fixed': appOptions.appSidebarFixed,
		'app-sidebar-minified': appOptions.appSidebarMinified, 
		'app-content-full-height': appOptions.appContentFullHeight, 
		'app-without-sidebar': appOptions.appSidebarNone, 
		'app-with-end-sidebar': appOptions.appSidebarEnd, 
		'app-with-two-sidebar': appOptions.appSidebarTwo,
		'app-with-wide-sidebar': appOptions.appSidebarWide,
		'app-with-light-sidebar': appOptions.appSidebarLight,
		'app-sidebar-mobile-toggled': appOptions.appSidebarMobileToggled,
		'app-sidebar-end-toggled': appOptions.appSidebarEndToggled,
		'app-sidebar-end-collapsed': !appOptions.appSidebarEndToggled,
		'app-sidebar-end-mobile-toggled': appOptions.appSidebarEndMobileToggled,
		'app-without-header': appOptions.appHeaderNone,
		'app-with-top-menu': appOptions.appTopMenu,
		'app-gradient-enabled': appOptions.appGradientEnabled,
		'has-scroll': appOptions.appBodyScrollTop
	}">
		<Header />
		<TopMenu v-if="appOptions.appTopMenu" />
		<Sidebar v-if="!appOptions.appSidebarNone" />
		<SidebarRight v-if="appOptions.appSidebarTwo" />
		<div id="content" class="app-content" v-bind:class="appOptions.appContentClass">
			<router-view></router-view>
			<vue-ins-progress-bar></vue-ins-progress-bar>
		</div>
		<SidebarRight v-if="appOptions.appSidebarTwo" />
		<ThemePanel v-on:change-theme="handleChangeTheme" />
		<ScrollTopBtn />
	</div>
	<div class="h-100" v-else>
		<router-view></router-view>
		<vue-ins-progress-bar></vue-ins-progress-bar>
	</div>
</template>

<script>
import Sidebar from './components/sidebar/Sidebar.vue'
import SidebarRight from './components/sidebar-right/SidebarRight.vue'
import Header from './components/header/Header.vue'
import TopMenu from './components/top-menu/TopMenu.vue'
import ThemePanel from './components/theme-panel/ThemePanel.vue'
import ScrollTopBtn from './components/scroll-top-btn/ScrollTopBtn.vue'
import AppOptions from './config/AppOptions.vue'

export default {
  name: 'app',
  components: {
    Sidebar,
    SidebarRight,
    Header,
    TopMenu,
    ThemePanel,
    ScrollTopBtn
  },
  data() {
		return {
			appOptions: AppOptions
		}
  },
	methods: {
		handleScroll: function() {
			AppOptions.appBodyScrollTop = window.scrollY;
		},
		handleSetColor: function() {
			AppOptions.color = {
				componentColor: window.getComputedStyle(document.body).getPropertyValue('--app-component-color').trim(),
				componentBg: window.getComputedStyle(document.body).getPropertyValue('--app-component-bg').trim(),
				dark: window.getComputedStyle(document.body).getPropertyValue('--bs-dark').trim(),
				light: window.getComputedStyle(document.body).getPropertyValue('--bs-light').trim(),
				blue: window.getComputedStyle(document.body).getPropertyValue('--bs-blue').trim(),
				indigo: window.getComputedStyle(document.body).getPropertyValue('--bs-indigo').trim(),
				purple: window.getComputedStyle(document.body).getPropertyValue('--bs-purple').trim(),
				pink: window.getComputedStyle(document.body).getPropertyValue('--bs-pink').trim(),
				red: window.getComputedStyle(document.body).getPropertyValue('--bs-red').trim(),
				orange: window.getComputedStyle(document.body).getPropertyValue('--bs-orange').trim(),
				yellow: window.getComputedStyle(document.body).getPropertyValue('--bs-yellow').trim(),
				green: window.getComputedStyle(document.body).getPropertyValue('--bs-green').trim(),
				success: window.getComputedStyle(document.body).getPropertyValue('--bs-success').trim(),
				teal: window.getComputedStyle(document.body).getPropertyValue('--bs-teal').trim(),
				cyan: window.getComputedStyle(document.body).getPropertyValue('--bs-cyan').trim(),
				white: window.getComputedStyle(document.body).getPropertyValue('--bs-white').trim(),
				gray: window.getComputedStyle(document.body).getPropertyValue('--bs-gray').trim(),
				lime: window.getComputedStyle(document.body).getPropertyValue('--bs-lime').trim(),
				gray100: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-100').trim(),
				gray200: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-200').trim(),
				gray300: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-300').trim(),
				gray400: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-400').trim(),
				gray500: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-500').trim(),
				gray600: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-600').trim(),
				gray700: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-700').trim(),
				gray800: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-800').trim(),
				gray900: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-900').trim(),
				black: window.getComputedStyle(document.body).getPropertyValue('--bs-black').trim(),
				componentColorRgb: window.getComputedStyle(document.body).getPropertyValue('--app-component-color-rgb').trim(),
				componentBgRgb: window.getComputedStyle(document.body).getPropertyValue('--app-component-bg-rgb').trim(),
				darkRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-dark-rgb').trim(),
				lightRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-light-rgb').trim(),
				blueRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-blue-rgb').trim(),
				indigoRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-indigo-rgb').trim(),
				purpleRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-purple-rgb').trim(),
				pinkRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-pink-rgb').trim(),
				redRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-red-rgb').trim(),
				orangeRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-orange-rgb').trim(),
				yellowRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-yellow-rgb').trim(),
				greenRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-green-rgb').trim(),
				successRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-success-rgb').trim(),
				tealRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-teal-rgb').trim(),
				cyanRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-cyan-rgb').trim(),
				whiteRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-white-rgb').trim(),
				grayRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-rgb').trim(),
				limeRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-lime-rgb').trim(),
				gray100Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-100-rgb').trim(),
				gray200Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-200-rgb').trim(),
				gray300Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-300-rgb').trim(),
				gray400Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-400-rgb').trim(),
				gray500Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-500-rgb').trim(),
				gray600Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-600-rgb').trim(),
				gray700Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-700-rgb').trim(),
				gray800Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-800-rgb').trim(),
				gray900Rgb: window.getComputedStyle(document.body).getPropertyValue('--bs-gray-900-rgb').trim(),
				blackRgb: window.getComputedStyle(document.body).getPropertyValue('--bs-black-rgb').trim()
			}
		},
		handleSetFont: function() {
			AppOptions.font = {
				family: window.getComputedStyle(document.body).getPropertyValue('--bs-body-font-family').trim(),
				size: window.getComputedStyle(document.body).getPropertyValue('--bs-body-font-size').trim(),
				weight: window.getComputedStyle(document.body).getPropertyValue('--bs-body-font-family').trim()
			}
		},
		handleSetDarkMode: function(value) {
			if (value === true) {
				document.body.classList.add('dark-mode');
			} else {
				document.body.classList.remove('dark-mode');
			}
			this.handleSetColor();
		},
		handleSetTheme: function(value) {
			var newTheme = 'theme-' + value;
			for (var x = 0; x < document.body.classList.length; x++) {
				if ((document.body.classList[x]).indexOf('theme-') > -1 && document.body.classList[x] !== newTheme) {
					document.body.classList.remove(document.body.classList[x]);
				}
			}
			document.body.classList.add(newTheme);
		},
		handleChangeTheme: function() {
			this.handleSetTheme(AppOptions.appTheme);
			this.handleSetDarkMode(AppOptions.appDarkMode);
		}
	},
  mounted () {
		this.$insProgress.finish()
  },
	created() {
		AppOptions.appBodyScrollTop = window.scrollY;
		
		window.addEventListener('scroll', this.handleScroll);
		
		this.$insProgress.start()
		this.handleSetColor();
		this.handleSetFont();
		
		if (AppOptions.appDarkMode) {
			this.handleSetDarkMode(true);
		}
		if (AppOptions.appTheme) {
			this.handleSetTheme(AppOptions.appTheme);
		}
		
		this.$router.beforeEach((to, from, next) => {
			this.$insProgress.start()
			if (AppOptions.appDarkMode) {
				this.handleSetDarkMode(true);
			}
			if (AppOptions.appTheme) {
				this.handleSetTheme(AppOptions.appTheme);
			}
			next()
		})
		this.$router.afterEach(() => {
			this.$insProgress.finish()
			AppOptions.appSidebarMobileToggled = false;
		})
	}
}
</script>
