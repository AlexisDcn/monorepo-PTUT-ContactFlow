import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const vuetify = createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'nouvelleCharte',
    themes: {
      nouvelleCharte: {
        dark: false,
        colors: {
          primary: '#2f2769',
          secondary: '#5f4e9b',
          accent: '#9059a0',
          error: '#ed6962',
          info: '#5f4e9b',
          success: '#9059a0',
          warning: '#ed6962',
        },
      },
    },
  },
});

export default vuetify;
