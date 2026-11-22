import React, { useEffect, useState } from 'react';

import {
  ActivityIndicator,
  FlatList,
  Pressable,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';

// User list item
function UserItem({ user, onPress }) {
  const initials = user.name
    .split(' ')
    .map((word) => word[0])
    .slice(0, 2)
    .join('');

  return (
    <Pressable style={styles.userItem} onPress={onPress}>
      <View style={styles.avatar}>
        <Text style={styles.avatarText}>{initials}</Text>
      </View>

      <View style={styles.userInfo}>
        <Text style={styles.userName}>{user.name}</Text>
        <Text style={styles.userEmail}>{user.email}</Text>
        <Text style={styles.username}>
          @{user.username}
        </Text>
      </View>

      <Text style={styles.arrow}>›</Text>
    </Pressable>
  );
}

// Reusable detail row
function DetailRow({ label, value }) {
  return (
    <View style={styles.detailRow}>
      <Text style={styles.detailLabel}>{label}</Text>
      <Text style={styles.detailValue}>
        {String(value)}
      </Text>
    </View>
  );
}

// Main application
export default function App() {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch users from REST API
  useEffect(() => {
    const loadUsers = async () => {
      try {
        setLoading(true);
        setError(null);

        const response = await fetch(
          'https://jsonplaceholder.typicode.com/users'
        );

        if (!response.ok) {
          throw new Error(
            `HTTP error: ${response.status}`
          );
        }

        const data = await response.json();
        setUsers(data);
      } catch (err) {
        setError('Unable to load users.');
      } finally {
        setLoading(false);
      }
    };

    loadUsers();
  }, []);

  // Loading screen
  if (loading) {
    return (
      <SafeAreaView style={styles.container}>
        <View style={styles.centered}>
          <ActivityIndicator
            size="large"
            color="#9B59B6"
          />
          <Text style={styles.message}>
            Loading users...
          </Text>
        </View>
      </SafeAreaView>
    );
  }

  // Error screen
  if (error) {
    return (
      <SafeAreaView style={styles.container}>
        <View style={styles.centered}>
          <Text style={styles.errorText}>
            {error}
          </Text>
        </View>
      </SafeAreaView>
    );
  }

  // User list screen
  if (selectedUser === null) {
    return (
      <SafeAreaView style={styles.container}>
        <View style={styles.header}>
          <Text style={styles.headerSmall}>
            USER MANAGEMENT
          </Text>

          <Text style={styles.headerTitle}>
            User Directory
          </Text>

          <Text style={styles.headerSubtitle}>
            Browse and explore user profiles
          </Text>
        </View>

        <View style={styles.listContainer}>
          <View style={styles.listHeading}>
            <Text style={styles.sectionHeading}>
              All Users
            </Text>

            <View style={styles.countBox}>
              <Text style={styles.countText}>
                {users.length} USERS
              </Text>
            </View>
          </View>

          <FlatList
            data={users}
            keyExtractor={(item) =>
              item.id.toString()
            }
            renderItem={({ item }) => (
              <UserItem
                user={item}
                onPress={() =>
                  setSelectedUser(item)
                }
              />
            )}
            contentContainerStyle={
              styles.listContent
            }
            showsVerticalScrollIndicator={false}
          />
        </View>
      </SafeAreaView>
    );
  }

  // User detail screen
  const initials = selectedUser.name
    .split(' ')
    .map((word) => word[0])
    .slice(0, 2)
    .join('');

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView
        contentContainerStyle={styles.details}
        showsVerticalScrollIndicator={false}
      >
        <Pressable
          style={styles.backButton}
          onPress={() => setSelectedUser(null)}
        >
          <Text style={styles.backButtonText}>
            ← BACK TO USERS
          </Text>
        </Pressable>

        <View style={styles.profileCard}>
          <View style={styles.largeAvatar}>
            <Text style={styles.largeAvatarText}>
              {initials}
            </Text>
          </View>

          <Text style={styles.profileName}>
            {selectedUser.name}
          </Text>

          <Text style={styles.profileUsername}>
            @{selectedUser.username}
          </Text>

          <Text style={styles.profileEmail}>
            {selectedUser.email}
          </Text>
        </View>

        <Text style={styles.sectionTitle}>
          PERSONAL INFORMATION
        </Text>

        <View style={styles.card}>
          <DetailRow
            label="User ID"
            value={selectedUser.id}
          />

          <DetailRow
            label="Full Name"
            value={selectedUser.name}
          />

          <DetailRow
            label="Username"
            value={selectedUser.username}
          />

          <DetailRow
            label="Email"
            value={selectedUser.email}
          />

          <DetailRow
            label="Phone"
            value={selectedUser.phone}
          />

          <DetailRow
            label="Website"
            value={selectedUser.website}
          />
        </View>

        <Text style={styles.sectionTitle}>
          ADDRESS
        </Text>

        <View style={styles.card}>
          <DetailRow
            label="Street"
            value={selectedUser.address.street}
          />

          <DetailRow
            label="Suite"
            value={selectedUser.address.suite}
          />

          <DetailRow
            label="City"
            value={selectedUser.address.city}
          />

          <DetailRow
            label="Zip Code"
            value={selectedUser.address.zipcode}
          />
        </View>

        <Text style={styles.sectionTitle}>
          GEOGRAPHIC COORDINATES
        </Text>

        <View style={styles.card}>
          <DetailRow
            label="Latitude"
            value={selectedUser.address.geo.lat}
          />

          <DetailRow
            label="Longitude"
            value={selectedUser.address.geo.lng}
          />
        </View>

        <Text style={styles.sectionTitle}>
          COMPANY INFORMATION
        </Text>

        <View style={styles.card}>
          <DetailRow
            label="Company Name"
            value={selectedUser.company.name}
          />

          <DetailRow
            label="Catch Phrase"
            value={selectedUser.company.catchPhrase}
          />

          <DetailRow
            label="Business"
            value={selectedUser.company.bs}
          />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

// Pink and purple sharp-edge design
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFF5FA',
  },

  centered: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },

  message: {
    marginTop: 12,
    fontSize: 16,
    color: '#8E5AA8',
  },

  errorText: {
    fontSize: 18,
    color: '#C83776',
    textAlign: 'center',
  },

  // Header
  header: {
    backgroundColor: '#A66DD4',
    paddingHorizontal: 24,
    paddingTop: 32,
    paddingBottom: 30,
    borderBottomWidth: 5,
    borderBottomColor: '#EEA6D7',
  },

  headerSmall: {
    fontSize: 11,
    fontWeight: 'bold',
    color: '#FBE3F4',
    letterSpacing: 2,
    marginBottom: 10,
  },

  headerTitle: {
    fontSize: 30,
    fontWeight: 'bold',
    color: '#FFFFFF',
  },

  headerSubtitle: {
    fontSize: 14,
    color: '#FFF0FC',
    marginTop: 8,
  },

  // User list
  listContainer: {
    flex: 1,
    paddingHorizontal: 18,
    paddingTop: 25,
  },

  listHeading: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 18,
  },

  sectionHeading: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#75449B',
  },

  countBox: {
    backgroundColor: '#F2D8F4',
    paddingHorizontal: 12,
    paddingVertical: 7,
  },

  countText: {
    fontSize: 11,
    color: '#81469D',
    fontWeight: 'bold',
    letterSpacing: 1,
  },

  listContent: {
    paddingBottom: 30,
  },

  // Square user cards
  userItem: {
    backgroundColor: '#FFFFFF',
    padding: 16,
    marginBottom: 12,
    flexDirection: 'row',
    alignItems: 'center',
    borderWidth: 1,
    borderColor: '#EBCBEA',
    borderLeftWidth: 4,
    borderLeftColor: '#D885BD',
  },

  // Square avatar
  avatar: {
    width: 52,
    height: 52,
    backgroundColor: '#F6D9EB',
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: 14,
  },

  avatarText: {
    color: '#9E4B91',
    fontSize: 18,
    fontWeight: 'bold',
  },

  userInfo: {
    flex: 1,
  },

  userName: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#63337D',
  },

  userEmail: {
    fontSize: 12,
    color: '#876B94',
    marginTop: 5,
  },

  username: {
    fontSize: 12,
    color: '#BC65A4',
    marginTop: 5,
    fontWeight: '500',
  },

  arrow: {
    fontSize: 30,
    color: '#B97CCE',
    marginLeft: 8,
  },

  // Details layout
  details: {
    padding: 18,
    paddingBottom: 45,
  },

  // Square back button
  backButton: {
    alignSelf: 'flex-start',
    backgroundColor: '#A66DD4',
    paddingVertical: 13,
    paddingHorizontal: 18,
    marginBottom: 20,
  },

  backButtonText: {
    color: '#FFFFFF',
    fontWeight: 'bold',
    fontSize: 12,
    letterSpacing: 0.8,
  },

  // Profile
  profileCard: {
    backgroundColor: '#FFFFFF',
    padding: 28,
    alignItems: 'center',
    borderWidth: 1,
    borderColor: '#EBCBEA',
    borderTopWidth: 5,
    borderTopColor: '#D885BD',
    marginBottom: 10,
  },

  largeAvatar: {
    width: 90,
    height: 90,
    backgroundColor: '#F6D9EB',
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 18,
    borderWidth: 2,
    borderColor: '#DCA1D1',
  },

  largeAvatarText: {
    fontSize: 30,
    fontWeight: 'bold',
    color: '#9E4B91',
  },

  profileName: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#63337D',
    textAlign: 'center',
  },

  profileUsername: {
    fontSize: 14,
    color: '#AB5CB6',
    marginTop: 7,
  },

  profileEmail: {
    fontSize: 13,
    color: '#876B94',
    marginTop: 8,
    textAlign: 'center',
  },

  // Section headings
  sectionTitle: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#794299',
    marginTop: 26,
    marginBottom: 12,
    letterSpacing: 1.2,
  },

  // Square detail cards
  card: {
    backgroundColor: '#FFFFFF',
    padding: 18,
    borderWidth: 1,
    borderColor: '#EBCBEA',
    borderLeftWidth: 4,
    borderLeftColor: '#B887DC',
  },

  detailRow: {
    paddingVertical: 11,
    borderBottomWidth: 1,
    borderBottomColor: '#F3E2F1',
  },

  detailLabel: {
    fontSize: 12,
    fontWeight: 'bold',
    color: '#AD65A8',
    marginBottom: 5,
  },

  detailValue: {
    fontSize: 16,
    color: '#63337D',
    lineHeight: 24,
  },
});
