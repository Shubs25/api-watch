import { useState } from 'react'
import Sidebar from './components/Sidebar'
import Header from './components/Header'
import Dashboard from './pages/Dashboard'
import AddEndpointModal, {
  type NewEndpoint,
} from './components/AddEndpointModal'
import { createEndpoint } from './services/endpointService'

function App() {
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [refreshTrigger, setRefreshTrigger] = useState(0)

  async function handleAddEndpoint(endpoint: NewEndpoint) {
    console.log(endpoint);
    const result = await createEndpoint(endpoint)

    console.log('Endpoint created:', result)
    setIsModalOpen(false)
    setRefreshTrigger(previous => previous + 1)
  }

  return (
    <div className="min-h-screen w-full bg-slate-950 flex">
      <Sidebar />

      <main className="flex-1 min-w-0 min-h-screen">
        <Header onAddEndpoint={() => setIsModalOpen(true)} />

        <Dashboard refreshTrigger={refreshTrigger} />
      </main>

      {isModalOpen && (
        <AddEndpointModal
          onClose={() => setIsModalOpen(false)}
          onSubmit={handleAddEndpoint}
        />
      )}
    </div>
  )
}

export default App