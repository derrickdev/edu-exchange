import { DM_Sans, Montserrat } from 'next/font/google'
import localFont from 'next/font/local'
 
// const montserrat = Montserrat({subsets: ['latin']})
// const dm_sans = DM_Sans({subsets: ['latin']})

const montserrat = localFont({src: '../assets/fonts/montserrat.ttf'})
const dm_sans = localFont({src: '../assets/fonts/dm_sans.ttf'})

export { montserrat, dm_sans }