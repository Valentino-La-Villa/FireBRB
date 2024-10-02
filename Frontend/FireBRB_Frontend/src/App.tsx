import 'bootstrap/dist/css/bootstrap.css'
import './styles/output.css'
import Footer from './components/footer/Footer'
import Header from './components/header/Header'
import { Route, Routes } from 'react-router-dom'
import Home from './components/homepage/Home'
import Register from './components/auth/register/Register'
import Login from './components/auth/login/Login'
import MyProfile from './components/myProfile/MyProfile'
import IndividualRentablePage from './components/rentables/IndividualRentablePage/IndividualRentablePage'
import RentableCatalog from './components/rentables/rentableCatalog/RentableCatalog'
import NotFound from './components/misc/NotFound'
import Checkout from './components/checkout/Checkout'
import Admin from './components/auth/admin/Admin'
import { useAuthRouterValidations } from './components/hooks/useAuthRouterValidations'
import useAxiosInterceptor from './components/hooks/useAxiosInterceptor'

function App() {

  const { LoginValidation, AdminValidation, NotLoggedInValidation } = useAuthRouterValidations()

  useAxiosInterceptor()

  return (
    <>
      <Header />

      <div className='body-wrapper bg-secondary'>

        <Routes>

          <Route path='/'
            element={
              <Home />
            }
          />

          <Route path='/register'
            element={
              <NotLoggedInValidation>
                <Register />
              </NotLoggedInValidation>
            }
          />

          <Route path='/login'
            element={
              <NotLoggedInValidation>
                <Login />
              </NotLoggedInValidation>
            }
          />

          <Route path='/myProfile'
            element={
              <LoginValidation>
                <MyProfile />
              </LoginValidation>
            }
          />

          <Route path='/rentables/'
            element={
              <LoginValidation>
                <RentableCatalog />
              </LoginValidation>
            }
          />

          <Route path='/rentables/:rentableID'
            element={
              <LoginValidation>
                <IndividualRentablePage />
              </LoginValidation>
            }
          />

          <Route path='/checkout'
            element={
              <LoginValidation>
                <Checkout />
              </LoginValidation>
            }
          />

          <Route path='/admin'
            element={
              <AdminValidation>
                <Admin />
              </AdminValidation>
            }
          />

          <Route path='/*'
            element={
              <NotFound />
            }
          />
        </Routes>

      </div>

      <Footer />
    </>
  )
}

export default App
