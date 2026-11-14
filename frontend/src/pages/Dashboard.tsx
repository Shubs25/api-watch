import { useEffect, useState } from 'react'
import EndpointTable from '../components/EndpointTable'
import { getEndpoints, triggerHealthCheck } from '../services/endpointService'
import type { Endpoint } from '../type/endpoint'


type DashboardProps = {
    refreshTrigger: number
}

function Dashboard({ refreshTrigger }: DashboardProps) {
    const [endpoints, setEndpoints] = useState<Endpoint[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    async function loadEndpoints() {
        try {
            setError(null)
            const data = await getEndpoints()
            setEndpoints(data)
        } catch (err) {
            setError(
                err instanceof Error ? err.message : 'Failed to load endpoints.'
            )
        } finally {
            setLoading(false)
        }
    }

    async function handleCheck(endpointId: string) {
        try {
            await triggerHealthCheck(endpointId)
        } catch (err) {
            setError(
                err instanceof Error ? err.message : 'Failed to trigger health check.'
            )
        }
    }

    useEffect(() => {
        loadEndpoints()
    }, [refreshTrigger])

    const totalEndpoints = endpoints.length
    const activeEndpoints = endpoints.filter(endpoint => endpoint.active).length
    const inactiveEndpoints = totalEndpoints - activeEndpoints

    return (
        <div className="p-8 space-y-8">
            {/* Keep your existing heading and summary-card layout here. */}

            {loading ? (
                <p className="text-slate-400">Loading endpoints...</p>
            ) : error ? (
                <p className="text-rose-400">{error}</p>
            ) : (
                <EndpointTable endpoints={endpoints} onCheck={handleCheck} />
            )}
        </div>
    )
}



export default Dashboard