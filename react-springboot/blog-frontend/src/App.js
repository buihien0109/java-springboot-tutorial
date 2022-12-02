import { Route, Routes } from 'react-router-dom';
import './App.css';
import LayoutAnonymous from './components/LayoutAnonymous';
import BlogDetail from './pages/anonymous/BlogDetail';
import ListBlog from './pages/anonymous/ListBlog';

function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={<LayoutAnonymous />}>
          <Route index element={<ListBlog />} />
          <Route path='blogs/:blogId/:slug' element={<BlogDetail />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
