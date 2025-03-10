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
          1: '#2f2769',
          2: '#5f4e9b',
          3: '#9059a0',
          4: '#ed6962'
        }
      }
    }
  }
})

export default vuetify;
